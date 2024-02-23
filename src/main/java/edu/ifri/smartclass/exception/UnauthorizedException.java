package edu.ifri.smartclass.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ToString
public class UnauthorizedException extends RuntimeException {

    private String errorCode;
    private String errorMessage;

    public UnauthorizedException(String message) {
        super(message);
        this.errorMessage = message;
    }

    public UnauthorizedException(String message, Throwable cause) {super(message, cause);}

    public UnauthorizedException(String message, String errorCode) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = message;
    }
}
