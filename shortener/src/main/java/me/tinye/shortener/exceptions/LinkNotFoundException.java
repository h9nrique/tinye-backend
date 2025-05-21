package me.tinye.shortener.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinkNotFoundException extends RuntimeException {

    private final String errorDescription;

    public LinkNotFoundException() {
        super("Link não existe");
        this.errorDescription = "O link que você tentou acessar não existe";
    }

    public LinkNotFoundException(String errorMessage, String errorDescription) {
        super(errorMessage);
        this.errorDescription = errorDescription;
    }

}
