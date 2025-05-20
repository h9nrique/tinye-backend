package me.tinye.shortener.repository;

import me.tinye.shortener.entity.Link;
import me.tinye.shortener.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LinkRepository extends JpaRepository<Link, UUID> {

    boolean existsByShortLink(String shortLink);

    Link findByShortLink(String shortLink);

    Optional<Link> findByIdAndDeletedFalse(UUID id);

    List<Link> findAllByUserAndDeleted(User user, boolean deleted);

}
