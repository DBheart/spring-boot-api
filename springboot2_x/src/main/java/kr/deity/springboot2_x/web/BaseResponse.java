package kr.deity.springboot2_x.web;

import lombok.Data;

@Data
public class BaseResponse {
    int status;
    String error;
    String message;

    public BaseResponse(){
        this.status = 200;
        this.error = "SUCCESS";
        this.message="";
    }
    public BaseResponse(String message) {
        this.status = 500;
        this.error="FAIL";
        this.message = message;
    }
}
