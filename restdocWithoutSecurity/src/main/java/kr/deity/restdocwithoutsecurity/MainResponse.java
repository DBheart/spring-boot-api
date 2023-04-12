package kr.deity.restdocwithoutsecurity;

import lombok.Data;

@Data
public class MainResponse {
    private String message;

    public MainResponse(String message){
        this.message = message;
    }
}
