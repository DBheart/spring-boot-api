package kr.deity.springboot3.lecture;

import kr.deity.springboot3.lecture.entity.Lecture;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lecture")
public class LectureController {

    final LectureService lectureService;

    @GetMapping("/")
    public List<Lecture> list(){
        return lectureService.list();
    }

    @PostMapping("/save")
    public void save(@RequestBody Lecture lecture) {
        lectureService.save(lecture);
    }

}
