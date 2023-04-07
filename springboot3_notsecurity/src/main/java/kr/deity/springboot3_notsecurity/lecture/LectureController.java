package kr.deity.springboot3_notsecurity.lecture;

import kr.deity.springboot3_notsecurity.lecture.entity.Lecture;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/lecture")
public class LectureController {

    final LectureService lectureService;

    @GetMapping("/")
    public List<Lecture> list(){
        List<Lecture> list = lectureService.list();
        System.out.println("list = " + list);
        return list;
    }

    @PostMapping(value="/save/")
    public Lecture save(@RequestBody  Lecture lecture) {
        System.out.println("lecture = " + lecture);

        return lecture;
    }

}
