package me.tinye.shortener.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateLinkRequestDTO {

    @NotBlank
    private String originalLink;
}
