package kr.deity.springboot.lecture;

import kr.deity.springboot.lecture.entity.Lecture;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lecture")
@RequiredArgsConstructor
public class LectureController {

    final LectureService lectureService;
    @GetMapping("/")
    public List<Lecture> listLecture(){
        return lectureService.list();
    }

    @PostMapping("/save")
    public void save(@RequestBody Lecture lecture) {
//        System.out.println("name = " + name);
        System.out.println("lecture = " + lecture);
//        lectureService.save(lecture);

    }
}
