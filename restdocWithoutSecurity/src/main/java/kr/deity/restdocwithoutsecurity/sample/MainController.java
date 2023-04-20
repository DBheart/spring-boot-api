package kr.deity.restdocwithoutsecurity.sample;

import kr.deity.restdocwithoutsecurity.sample.MainResponse;
import kr.deity.restdocwithoutsecurity.sample.SimpleDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sample")
public class MainController {

    @GetMapping
    public MainResponse get(){
        System.out.println("test user get");
        return new MainResponse("get test success");
    }

    @GetMapping("/get/{userId}")
    public void getPath(@PathVariable String userId){
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

    @GetMapping("/list")
    public List<SimpleDto> list(){
        SimpleDto simpleDto = new SimpleDto("test");
        SimpleDto simpleDto2 = new SimpleDto("test2","url2");

        return new ArrayList<>();
    }

}
