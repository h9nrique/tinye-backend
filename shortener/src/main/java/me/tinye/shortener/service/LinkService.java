package me.tinye.shortener.service;

import me.tinye.shortener.DTO.AccessLinkResponseDTO;
import me.tinye.shortener.DTO.CreateLinkRequestDTO;
import me.tinye.shortener.commom.session.GetUserService;
import me.tinye.shortener.entity.Link;
import me.tinye.shortener.entity.User;
import me.tinye.shortener.repository.LinkRepository;
import me.tinye.shortener.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;

@Service
public class LinkService {

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private GetUserService getUserService;

    @Autowired
    private UserRepository userRepository;

    public Link createLink(CreateLinkRequestDTO data) {
        User userAuth = getUserService.execute();

        Link link = new Link();
        link.setOriginalLink(data.getOriginalLink());
        link.setShortLink(createRandomLink());

        if(userAuth != null) {
            User user = userRepository.findByEmail(userAuth.getEmail());
            link.setUser(user);
        }

        return linkRepository.saveAndFlush(link);
    }

    public AccessLinkResponseDTO accessLink(String shortLink) {
        Link link = linkRepository.findByShortLink(shortLink);

        if(link == null) {
            throw new Error("Link não existe");
        }

        if(link.isDeleted()) {
            throw new Error("O link foi deletado");
        }

        link.setAccessCount(link.getAccessCount() + 1);

        linkRepository.saveAndFlush(link);

        return AccessLinkResponseDTO.builder()
                .originalLink(link.getOriginalLink())
                .build();
    }

    public void deleteLink(Long id) {
        User user = userRepository.findByEmail(getUserService.execute().getEmail());
        Link link = linkRepository.findById(id).orElse(null);

        if (link == null) {
            throw new Error("Link não existe");
        }

        if (!link.getUser().getId().equals(user.getId())) {
            throw new Error("Sem permissão");
        }

        link.setDeleted(true);

        linkRepository.saveAndFlush(link);
    }

    private String createRandomLink() {
        String randomLink;

        do {
            randomLink = RandomStringUtils.randomAlphanumeric(6);
        } while (linkRepository.existsByShortLink(randomLink));

        return randomLink;
    }
}
