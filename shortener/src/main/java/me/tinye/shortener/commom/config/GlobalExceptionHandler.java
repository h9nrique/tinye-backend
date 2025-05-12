package me.tinye.shortener.commom.config;

import me.tinye.shortener.DTO.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({Error.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDTO handle(Error exception) {
        return new ErrorDTO(exception.getMessage(), HttpStatus.BAD_REQUEST.value());
    }
}
