package com.dzo.test_bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName, String fieldValue, String fieldName) {
        super(String.format("%s not found with %s = %s", resourceName, fieldName, fieldValue));
    }

    public ResourceNotFoundException(String resourceName) {
        super(String.format("%s not found", resourceName));
    }
}
