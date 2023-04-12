package com.example.restdocwithswagger.sample;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/sample")
public class MainController {

    @GetMapping
    public ResponseEntity<MainResponse> get(){
        System.out.println("test user get");
        return ResponseEntity.ok(new MainResponse("get test success"));
    }

    @GetMapping("/get/{userId}")
    public void get(@PathVariable String userId){
        System.out.println("userId path= " + userId);
    }

    @GetMapping("/param")
    public void getUser(@RequestParam String userId){
        System.out.println("userId param= " + userId);
    }

    @PostMapping
    public void save(@RequestParam String message){
        System.out.println("mainResponse = " + message);
    }

    @PostMapping("/dto")
    public void save(@Valid MainResponse mainResponse){
        System.out.println("mainResponse = " + mainResponse);
    }

}
