package com.example.restdocwithswagger.lecture;

import com.example.restdocwithswagger.lecture.entity.Lecture;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/lecture")
public class LectureController {

    final LectureService lectureService;

    @GetMapping("/list")
    public List<Lecture> list() {
        System.out.println("list is null");
//        return null;
        return lectureService.list();
    }

    @PostMapping("/save")
    public void save(Lecture lecture) {
        System.out.println("save");
        lectureService.save(lecture);
    }
}
