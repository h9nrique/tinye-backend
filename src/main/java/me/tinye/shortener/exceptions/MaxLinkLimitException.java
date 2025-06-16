package me.tinye.shortener.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaxLinkLimitException extends RuntimeException {

    private final String errorDescription;

    public MaxLinkLimitException() {
        super("Limite de 10 links atingido");
        this.errorDescription = "Com o plano básico, você pode criar até 10 links.";
    }
}
