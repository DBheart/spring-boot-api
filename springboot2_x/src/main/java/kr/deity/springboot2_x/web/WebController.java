package kr.deity.springboot2_x.web;

import kr.deity.springboot2_x.web.exception.InvalidInputException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/web")
public class WebController {
    @GetMapping("/list")
    public DataResponse<List<WebDataResponse>> list() {
        List<WebDataResponse> list = new ArrayList<>();

//        Map<String, String> data = new HashMap<>();
//
//        data.put("1", "test1");
//        data.put("2", "test2");
//        list.add(data);

        WebDataResponse data1 = new WebDataResponse("id1", "name1");
        WebDataResponse data2 = new WebDataResponse("id2", "name2");


        list.add(data1);
        list.add(data2);

        return new DataResponse(list);
    }

    record WebDataResponse(String id, String name) {
    }

    @GetMapping("/list/500")
    public DataResponse<Map<String,String>> list500() {
        //Exception 따라서 값이 달라진다. 어떻게 해야할까?

        throw new InvalidInputException("일치하는 정보가 없습니다");
//        return new BaseResponse("오류가 발생하였습니다.");

    }

    @GetMapping("/list/notlist")
    public void listNot(){

    }
}
