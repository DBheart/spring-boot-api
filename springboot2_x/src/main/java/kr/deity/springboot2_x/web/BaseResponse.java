package kr.deity.springboot2_x.web;

import lombok.Data;

@Data
public class BaseResponse {
    String code;
    String message;

    public BaseResponse(){
        this.code = "SUCCESS";
        this.message="";
    }
    public BaseResponse(String message) {
        this.code="FAIL";
        this.message = message;
    }
}
