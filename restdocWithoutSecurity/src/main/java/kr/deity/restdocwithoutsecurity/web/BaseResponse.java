package kr.deity.restdocwithoutsecurity.web;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BaseResponse {
    int status;
    String error;
    String message;

    public BaseResponse() {
        this.status = HttpStatus.OK.value();
        this.error = "SUCCESS";
    }

    public BaseResponse(String message) {
        this.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.error = "FAIL";
        this.message = message;

    }
}
