package com.example.restdocwithswagger.common;

import lombok.Data;


@Data
public class BaseResponse<T> {

    String status = "SUCCESS";
    /**
     * 상태 코드(숫자)
     */
    int code = 200;
    /**
     * 결과 메시지
     */
    String message = "Requested Successful";
    /**
     * 반환 데이터
     */
    T data = null;
}
