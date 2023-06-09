package kr.deity.springboot2_x.web;

import lombok.Getter;

@Getter
public class DataResponse<T> extends BaseResponse {
    private T data;
    public DataResponse(T data){
        super();
        this.data = data;
    }
}
