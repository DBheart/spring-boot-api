package kr.deity.restdocwithoutsecurity.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResponseEntityException {

    @ExceptionHandler(ServerErrorException.class)
    protected ResponseEntity<BaseResponse> invalidInputException(ServerErrorException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BaseResponse(e.getMessage()));
    }
}
