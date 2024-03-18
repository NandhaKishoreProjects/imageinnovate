package com.example.demo.controllers;

import com.example.demo.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ApiResponse<String,String>> resourceNotFoundException(Exception ex, WebRequest request) {

        return new ResponseEntity<ApiResponse<String,String>>(ApiResponse.<String,String>builder().errors(ex.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }
}
