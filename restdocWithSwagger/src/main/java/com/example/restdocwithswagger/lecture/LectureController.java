package com.example.restdocwithswagger.lecture;

import com.example.restdocwithswagger.common.BaseResponse;
import com.example.restdocwithswagger.lecture.entity.Lecture;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/lecture")
public class LectureController {

    final LectureService lectureService;

    @GetMapping("/list")
    public BaseResponse<List<Lecture>> list() {

        List<Lecture> list = lectureService.list();

        BaseResponse<List<Lecture>> rtnLecture = new BaseResponse<>();

        return rtnLecture;
    }

    @PostMapping("/save")
    public List<Lecture> save(@Valid Lecture lecture) {
        System.out.println("save");
        lectureService.save(lecture);
        return lectureService.list();
    }
}
