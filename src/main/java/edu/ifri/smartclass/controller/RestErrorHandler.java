package edu.ifri.smartclass.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import edu.ifri.smartclass.enums.StatusMessage;
import edu.ifri.smartclass.exception.AppException;
import edu.ifri.smartclass.exception.BadRequestException;
import edu.ifri.smartclass.exception.NoContentException;
import edu.ifri.smartclass.exception.NotFoundException;
import edu.ifri.smartclass.exception.ResourceNotFoundException;
import edu.ifri.smartclass.exception.UnauthorizedException;
import edu.ifri.smartclass.payload.ApiResponse;

@RestControllerAdvice
public class RestErrorHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return new ApiResponse(false, StatusMessage.BAD_REQUEST_ERROR.getMessage(),
                HttpStatus.BAD_REQUEST, StatusMessage.BAD_REQUEST_ERROR.getCode(), errors);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ApiResponse handleMethodArgumentNotValid(BadRequestException ex) {
        return new ApiResponse(false, StatusMessage.BAD_REQUEST_ERROR.getMessage() + ": " + ex.getErrorMessage(),
                HttpStatus.BAD_REQUEST, ex.getErrorCode(), new HashMap<>());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(AppException.class)
    public ApiResponse handleAppException(AppException ex) {
        return new ApiResponse(false, StatusMessage.INTERNAL_SERVER_ERROR.getMessage() + ": " + ex.getErrorMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR, ex.getErrorCode(), new HashMap<>());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(NoContentException.class)
    public ApiResponse handleNoContentException(NoContentException ex) {
        return new ApiResponse(false, StatusMessage.NO_CONTENT_ERROR.getMessage(),
                HttpStatus.NO_CONTENT, ex.getErrorCode(), new HashMap<>());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ApiResponse handleNotFoundException(NotFoundException ex) {
        return new ApiResponse(false, StatusMessage.NOT_FOUND_ERROR.getMessage() + ": " + ex.getErrorMessage(),
                HttpStatus.NO_CONTENT, ex.getErrorCode(), new HashMap<>());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ApiResponse handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ApiResponse(false, StatusMessage.NOT_FOUND_ERROR.getMessage() + ": " + ex.getErrorMessage(),
                HttpStatus.NO_CONTENT, ex.getErrorCode(), new HashMap<>());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public ApiResponse handleResourceUnauthorizedException(UnauthorizedException ex) {
        return new ApiResponse(false, StatusMessage.NOT_ALLOWED.getMessage() + ": " + ex.getErrorMessage(),
                HttpStatus.UNAUTHORIZED, ex.getErrorCode(), new HashMap<>());
    }
}
