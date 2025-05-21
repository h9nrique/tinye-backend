package me.tinye.shortener.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailAlreadyRegisteredException extends RuntimeException {

    private final String errorDescription;

    public EmailAlreadyRegisteredException() {
        super("E-mail já cadastrado");
        this.errorDescription = "Já existe uma conta associada a este e-mail. Tente fazer login ou use outro e-mail para se cadastrar.";
    }

    public EmailAlreadyRegisteredException(String errorMessage, String errorDescription) {
        super(errorMessage);
        this.errorDescription = errorDescription;
    }

}
