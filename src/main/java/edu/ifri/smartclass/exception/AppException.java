package edu.ifri.smartclass.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@ToString
public class AppException extends RuntimeException {
    private String errorCode;
    private String errorMessage;

    public AppException(String message) {
        super(message);
        this.errorMessage = message;
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(String message, String errorCode) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = message;
    }
}