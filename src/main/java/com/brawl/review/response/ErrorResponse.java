package com.brawl.review.response;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ErrorResponse {
    private final String code;
    private final String message;

    private Map<String, String> validation = new HashMap<>();

    public void addValitationField(String field, String message){
        validation.put(field, message);
    }

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
