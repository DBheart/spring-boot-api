package com.example.restdocwithswagger.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerErrorException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/web")
public class WebController {


    @RequestMapping("/list")
    public List<WebData> list(){

        List<WebData> list = new ArrayList<>();

        WebData webData = new WebData("deity", "name1");
        WebData webData2 = new WebData("deity2", "name2");

        list.add(webData);
        list.add(webData2);

        return list;
    }

    record WebData(String id, String name) {
    }

    @RequestMapping("/500")
    public void list500(){

        throw new ServerErrorException("서버오류가 발생하였습니다.");
    }

}
