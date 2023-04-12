package kr.deity.restdocwithoutsecurity;

import lombok.Data;

@Data
public class SimpleDto {
    private String message;
    private String url;


    public SimpleDto(String message) {
        this.message = message;
    }
    public SimpleDto(String message, String url) {
        this.message = message;
        this.url = url;
    }
}
