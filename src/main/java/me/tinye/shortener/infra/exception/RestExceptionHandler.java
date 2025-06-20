package me.tinye.shortener.infra.exception;

import me.tinye.shortener.DTO.ExceptionDTO;
import me.tinye.shortener.exceptions.*;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(1)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(LinkNotFoundException.class)
    private ResponseEntity<ExceptionDTO> linkNotFoundHandler(LinkNotFoundException exception) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ExceptionDTO errorResponse = new ExceptionDTO(
                status.value(),
                status,
                exception.getMessage(),
                exception.getErrorDescription()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(UnauthorizedException.class)
    private ResponseEntity<ExceptionDTO> unauthorizedExceptionHandler(UnauthorizedException exception) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        ExceptionDTO errorResponse = new ExceptionDTO(
                status.value(),
                status,
                exception.getMessage(),
                exception.getErrorDescription()
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    private ResponseEntity<ExceptionDTO> EmailAlreadyRegisteredHandler(EmailAlreadyRegisteredException exception) {
        HttpStatus status = HttpStatus.CONFLICT;
        ExceptionDTO errorResponse = new ExceptionDTO(
                status.value(),
                status,
                exception.getMessage(),
                exception.getErrorDescription()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(InvalidLoginException.class)
    private ResponseEntity<ExceptionDTO> InvalidLoginExceptionHandler(InvalidLoginException exception) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        ExceptionDTO errorResponse = new ExceptionDTO(
                status.value(),
                status,
                exception.getMessage(),
                exception.getErrorDescription()
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(MaxLinkLimitException.class)
    private ResponseEntity<ExceptionDTO> MaxLinkLimitExceptionHandler(MaxLinkLimitException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionDTO errorResponse = new ExceptionDTO(
                status.value(),
                status,
                exception.getMessage(),
                exception.getErrorDescription()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

}
