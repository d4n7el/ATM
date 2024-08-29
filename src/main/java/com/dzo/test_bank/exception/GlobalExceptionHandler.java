package com.dzo.test_bank.exception;

import com.dzo.test_bank.payload.ApiResponse;
import com.dzo.test_bank.util.ErrorMessageUtil;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(ConstraintViolationException e, WebRequest webRequest) {
        String errorMessage = e.getMessage();
        Map<String, String> mapErrors = ErrorMessageUtil.generateErrorMessage(errorMessage);
        ApiResponse response = new ApiResponse(webRequest.getDescription(false), mapErrors );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, WebRequest webRequest) {
        Map<String, String> mapErrors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            mapErrors.put(fieldName, errorMessage);
        });
        ApiResponse response = new ApiResponse(webRequest.getDescription(false),  mapErrors );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ApiResponse> handleNullPointerException(NullPointerException e, WebRequest webRequest) {
        String errorMessage = e.getMessage();
        Map<String, String> mapErrors = ErrorMessageUtil.generateErrorMessage(errorMessage);
        ApiResponse response = new ApiResponse(webRequest.getDescription(false), mapErrors );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse> handlerDataIntegrityViolationException(DataIntegrityViolationException e, WebRequest webRequest) {
        String errorMessage = e.getMostSpecificCause().getMessage();
        Map<String, String> mapErrors = ErrorMessageUtil.generateErrorMessage(errorMessage);
        ApiResponse response = new ApiResponse(webRequest.getDescription(false), mapErrors );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity<ApiResponse> handlerDataIntegrityViolationException(InvalidDataAccessApiUsageException e, WebRequest webRequest) {
        String errorMessage = e.getMostSpecificCause().getMessage();
        Map<String, String> mapErrors = ErrorMessageUtil.generateErrorMessage(errorMessage);
        ApiResponse response = new ApiResponse(webRequest.getDescription(false), mapErrors );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(IllegalStateException e, WebRequest webRequest) {
        ApiResponse response = new ApiResponse(webRequest.getDescription(false), e.getMessage() );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException e, WebRequest webRequest) {
        ApiResponse response = new ApiResponse(webRequest.getDescription(false), e.getMessage() );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
