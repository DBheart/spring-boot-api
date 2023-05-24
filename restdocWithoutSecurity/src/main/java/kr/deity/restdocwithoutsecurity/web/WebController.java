package kr.deity.restdocwithoutsecurity.web;

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


    @GetMapping("/list/recode")
    public List<WebDataResponse> listRecode() {
        List<WebDataResponse> list = new ArrayList<>();

        WebDataResponse data1 = new WebDataResponse("id1", "name1");
        WebDataResponse data2 = new WebDataResponse("id2", "name2");


        list.add(data1);
        list.add(data2);

        return list;
    }

    record WebDataResponse(String id, String name) {}

    @GetMapping("/list/500")
    public void listError(){
        throw new ServerErrorException("서버오류가 발생하였습니다.");
    }
}
