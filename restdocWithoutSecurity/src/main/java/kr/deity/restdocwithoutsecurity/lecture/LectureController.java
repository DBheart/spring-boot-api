package kr.deity.restdocwithoutsecurity.lecture;

import kr.deity.restdocwithoutsecurity.lecture.entity.Lecture;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/lecture")
@RequiredArgsConstructor
public class LectureController {

    final LectureService lectureService;

    @GetMapping
    public List<Lecture> lectureList() {
        return lectureService.list();
    }

    @PostMapping
    public List<Lecture> saveLecture(@Valid Lecture lecture){
        lectureService.register(lecture);

        return lectureService.list();

        //redirect 나을까? 아님 그냥 데이터를 넣는게 나을까?
    }

}
