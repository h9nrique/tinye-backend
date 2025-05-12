package me.tinye.shortener.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Getter
@Setter
@Entity
@Table(name = "link_tb")
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

    @Column
    private boolean deleted = false;
}
