package com.dzo.test_bank.controller.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class ApiResponseError {
    private String message = "";
    private Date date = new Date();
    private String path;
    Map<String, String> errors = new HashMap<>();

    public ApiResponseError(String path, String message, Map<String, String> mapErrors) {
        this.path = path.replace("uri=", "");
        this.message = message;
        this.errors = mapErrors;
    }

    public ApiResponseError(String path, Map<String, String> mapErrors) {
        this.path = path.replace("uri=", "");
        this.errors = mapErrors;
    }

    public ApiResponseError(String path, String message) {
        this.path = path.replace("uri=", "");
        this.message = message;
    }
}
