package me.tinye.shortener.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class ExceptionDTO {

    private int httpStatusCode;

    private HttpStatus httpStatus;

    private String errorMessage;

    private String errorDescription;
}
