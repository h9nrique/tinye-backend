package me.tinye.shortener.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
