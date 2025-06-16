package me.tinye.shortener.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@Entity(name = "links")
@Table(name = "links")
@ToString
public class Link extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "short_link")
    @NotNull
    private String shortLink;

    @Column(name = "original_link")
    @NotNull
    private String originalLink;

    @Column(name = "access_count")
    private int accessCount = 0;

    @Column(name = "active")
    private boolean active = true;

    @Column(name = "deleted")
    private boolean deleted = false;

    @ManyToOne(optional = true)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;
}
