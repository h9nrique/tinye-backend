package me.tinye.shortener.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import me.tinye.shortener.entity.Link;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
public class LinkResponseDTO {

    private UUID id;

    private String shortLink;

    private String originalLink;

    private int accessCount;

    private boolean active;

    private Timestamp createdAt;

    private Timestamp updateAt;

    public LinkResponseDTO () { };

    public LinkResponseDTO(UUID id, String shortLink, int accessCount, String originalLink, boolean active, Timestamp createdAt, Timestamp updateAt) {
        this.id = id;
        this.shortLink = shortLink;
        this.accessCount = accessCount;
        this.originalLink = originalLink;
        this.active = active;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    public LinkResponseDTO(Link link) {
        this.id = link.getId();
        this.shortLink = link.getShortLink();
        this.accessCount = link.getAccessCount();
        this.originalLink = link.getOriginalLink();
        this.active = link.isActive();
        this.createdAt = link.getCreatedAt();
        this.updateAt = link.getUpdatedAt();
    }
}
