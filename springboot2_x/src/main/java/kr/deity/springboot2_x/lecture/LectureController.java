package kr.deity.springboot2_x.lecture;

import kr.deity.springboot2_x.lecture.entity.Lecture;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lecture")
@RequiredArgsConstructor
public class LectureController {

    final LectureService lectureService;
    @GetMapping("/list")
    public List<Lecture> listLecutre(){
        return lectureService.list();
    }

    @PostMapping("/saveSpringBootRquestParam")
    public void saveSpringBoot2(@RequestParam String name) {
        Lecture lecture = new Lecture();
        lecture.setName(name);

        lectureService.save(lecture);

    }

    @PostMapping("/saveSpringBootRquestDto")
    public void saveSpringBootRquestDto(@RequestBody Lecture lecture) {
//        Lecture lecture = new Lecture();
//        lecture.setName(name);

        lectureService.save(lecture);

    }
}
