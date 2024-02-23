package edu.ifri.smartclass.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@ToString
public class ResourceNotFoundException extends RuntimeException {
    private String errorCode;
    private String errorMessage;

    public ResourceNotFoundException(String message) {
        super(message);
        this.errorMessage = message;
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(String message, String errorCode) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = message;
    }

}
