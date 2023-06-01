package com.example.restdocwithswagger.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ServerErrorException;

import java.rmi.ServerException;

@ControllerAdvice
public class ResponseEntityException {

    @ExceptionHandler(ServerErrorException.class)
    public ResponseEntity<BaseResponse> serverException(ServerErrorException e){
        return ResponseEntity.internalServerError().body(new BaseResponse(e.getMessage()));
    }
}
