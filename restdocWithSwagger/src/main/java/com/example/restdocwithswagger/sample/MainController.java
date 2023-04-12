package com.example.restdocwithswagger.sample;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class MainController {

    @GetMapping
    public ResponseEntity<MainResponse> get(){
        return ResponseEntity.ok(new MainResponse("get test success"));
    }


    @Data
    private class MainResponse {
        private String message;

        public MainResponse(String message){
            this.message = message;
        }
    }

}
