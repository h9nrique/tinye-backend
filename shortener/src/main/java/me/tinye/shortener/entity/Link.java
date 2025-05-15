package me.tinye.shortener.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity(name = "links")
@Table(name = "links")
public class Link extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "short_link")
    @NotNull
    private String shortLink;

    @Column(name = "original_link")
    @NotNull
    private String originalLink;

    @Column(name = "access_count")
    private int accessCount = 0;

    @Column(name = "deleted")
    private boolean deleted = false;

    @ManyToOne(optional = true)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;
}
