package me.tinye.shortener.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnauthorizedException extends RuntimeException {

    private final String errorDescription;

    public UnauthorizedException() {
        super("Acesso não autorizado");
        this.errorDescription = "Você não tem permissão para realizar essa ação";
    }

    public UnauthorizedException(String errorMessage, String errorDescription) {
        super(errorMessage);
        this.errorDescription = errorDescription;
    }

}
