package kr.deity.springboot2_x.sample;

import lombok.Data;

@Data
public class MainResponse {
    private String message;

    public MainResponse(String message){
        this.message = message;
    }
}
