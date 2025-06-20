package me.tinye.shortener.service;

import me.tinye.shortener.DTO.AccessLinkResponseDTO;
import me.tinye.shortener.DTO.CreateLinkRequestDTO;
import me.tinye.shortener.DTO.LinkResponseDTO;
import me.tinye.shortener.commom.session.GetUserService;
import me.tinye.shortener.entity.Link;
import me.tinye.shortener.entity.User;
import me.tinye.shortener.exceptions.LinkNotFoundException;
import me.tinye.shortener.exceptions.MaxLinkLimitException;
import me.tinye.shortener.exceptions.UnauthorizedException;
import me.tinye.shortener.repository.LinkRepository;
import me.tinye.shortener.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LinkService {

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private GetUserService getUserService;

    @Autowired
    private UserRepository userRepository;

    public LinkResponseDTO createLink(CreateLinkRequestDTO data) {
        User userAuth = getUserService.execute();

        Link newLink = new Link();
        newLink.setOriginalLink(data.getOriginalLink());
        newLink.setShortLink(createRandomLink());

        if(userAuth != null) {
            User user = userRepository.findByEmail(userAuth.getEmail());
            List<Link> allLinks = linkRepository.findAllByUserAndDeleted(user, false);
            if(allLinks.size() >= 10) {
                throw new MaxLinkLimitException();
            }
            newLink.setUser(user);
        }

        Link link = linkRepository.saveAndFlush(newLink);

        return new LinkResponseDTO(link);

    }

    public AccessLinkResponseDTO accessLink(String shortLink) {
        Link link = linkRepository.findByShortLink(shortLink);

        if(link == null || link.isDeleted()) {
            throw new LinkNotFoundException();
        }

        if(!link.isActive()) {
            throw new LinkNotFoundException();
        }

        link.setAccessCount(link.getAccessCount() + 1);

        linkRepository.saveAndFlush(link);

        return AccessLinkResponseDTO.builder()
                .originalLink(link.getOriginalLink())
                .build();
    }

    public void deleteLink(UUID id) {
        User user = userRepository.findByEmail(getUserService.execute().getEmail());
        Link link = linkRepository.findByIdAndDeletedFalse(id).orElseThrow(LinkNotFoundException::new);

        if (link.getUser() == null || !link.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedException();
        }

        link.setDeleted(true);

        linkRepository.saveAndFlush(link);
    }

    public List<LinkResponseDTO> getLinks() {
        User user = userRepository.findByEmail(getUserService.execute().getEmail());

        List<Link> links = linkRepository.findAllByUserAndDeleted(user, false);

        return links.stream()
                .map(LinkResponseDTO::new)
                .collect(Collectors.toList());
    };

    public LinkResponseDTO changeStatus(UUID id) {
        User user = userRepository.findByEmail(getUserService.execute().getEmail());
        Link link = linkRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(LinkNotFoundException::new);

        if (link.getUser() == null || !link.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedException();
        }

        link.setActive(!link.isActive());

        Link changedLink = linkRepository.saveAndFlush(link);

        return new LinkResponseDTO(changedLink);
    }

    private String createRandomLink() {
        String randomLink;

        do {
            randomLink = RandomStringUtils.randomAlphanumeric(6);
        } while (linkRepository.existsByShortLink(randomLink));

        return randomLink;
    }
}
