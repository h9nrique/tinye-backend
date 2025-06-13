package me.tinye.shortener.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidLoginException extends RuntimeException {

    private final String errorDescription;

    public InvalidLoginException() {
        super("Usuário ou senha incorretos.");
        this.errorDescription = "Verifique os dados e tente novamente.";
    }

}
