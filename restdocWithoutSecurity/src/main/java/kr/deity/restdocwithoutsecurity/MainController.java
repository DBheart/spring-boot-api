package kr.deity.restdocwithoutsecurity;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
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

    @GetMapping("/list")
    public List<SimpleDto> list(){
        SimpleDto simpleDto = new SimpleDto("test");
        SimpleDto simpleDto2 = new SimpleDto("test2","url2");

        return List.of(simpleDto, simpleDto2);
    }

}
