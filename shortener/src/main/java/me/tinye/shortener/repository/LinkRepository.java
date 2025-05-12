package me.tinye.shortener.repository;

import me.tinye.shortener.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link, Long> {

    boolean existsByShortLink(String shortLink);

    Link findByShortLink(String shortLink);

}
