package edu.ifri.smartclass.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ResponseStatus(HttpStatus.NO_CONTENT)
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@ToString
public class NoContentException extends RuntimeException{
    private String errorCode;
    private String errorMessage;

    public NoContentException(String message) {
        super(message);
    }

    public NoContentException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoContentException(String message, String errorCode) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = message;
    }
}
