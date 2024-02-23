package edu.ifri.smartclass.payload;

import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class ApiResponse {
    private HttpStatus statusCode;
    private String errorCode;
    private Boolean success;
    private String message;
    private Map<String,String> errors;
    private Map<String,String> warnings;
    private Object data;

    /**
     *
     * @param success
     * @param message
     * @param data
     * @param statusCode
     */
    public ApiResponse(Boolean success, String message, HttpStatus statusCode, Object data) {
        this.success = success;
        this.message = message;
        this.statusCode = statusCode;
        this.data = data;
    }

    /**
     *
     * @param success
     * @param message
     * @param statusCode
     * @param errorCode
     * @param errors
     */
    public ApiResponse(Boolean success, String message, HttpStatus statusCode, String errorCode, Map<String,String> errors) {
        this.success = success;
        this.message = message;
        this.statusCode = statusCode;
        this.errors = errors;
        this.errorCode = errorCode;
    }

    /**
     *
     * @param success
     * @param message
     * @param statusCode
     * @param warnings
     */
    public ApiResponse(Boolean success, String message, HttpStatus statusCode, Map<String,String> warnings) {
        this.success = success;
        this.message = message;
        this.statusCode = statusCode;
        this.warnings = warnings;
    }

    /**
     *
     * @param success
     * @param message
     * @param statusCode
     */
    public ApiResponse(Boolean success, String message, HttpStatus statusCode) {
        this.success = success;
        this.message = message;
        this.statusCode = statusCode;
    }
}
