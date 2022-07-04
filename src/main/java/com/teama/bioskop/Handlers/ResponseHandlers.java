package com.teama.bioskop.Handlers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

public class ResponseHandlers {
    public static ResponseEntity<?> generateResponse(String errorMessage, HttpStatus status, HttpHeaders headers, ZonedDateTime accessedTime, Object response){
        Map<String, Object> map = new HashMap<>();
        map.put("error_message", errorMessage);
        map.put("accessed_time", accessedTime);
        map.put("data", response);

        return new ResponseEntity<>(map, headers, status);
    }
}