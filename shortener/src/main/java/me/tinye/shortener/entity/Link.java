package me.tinye.shortener.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

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
}
