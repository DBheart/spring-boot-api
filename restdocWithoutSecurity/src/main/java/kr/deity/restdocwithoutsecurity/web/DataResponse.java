package kr.deity.restdocwithoutsecurity.web;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DataResponse<T> extends BaseResponse{
    private T data;

    public DataResponse(T data){
        super();
        this.data = data;
    }
}
