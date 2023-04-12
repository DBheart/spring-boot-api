package com.example.restdocwithswagger.lecture;

import com.example.restdocwithswagger.common.BaseResponse;
import com.example.restdocwithswagger.lecture.entity.Lecture;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<BaseResponse<List<Lecture>>> list() {
        System.out.println("list is null");
        List<Lecture> list = lectureService.list();
        System.out.println("list = " + list);
//        return null;
        Lecture lecture = new Lecture();
        lecture.setName("수학");

        list.add(lecture);

        BaseResponse<List<Lecture>> rtnLecture = new BaseResponse<>();
        rtnLecture.setData(list);

        return ResponseEntity.ok(rtnLecture);
    }

    @PostMapping("/save")
    public void save(Lecture lecture) {
        System.out.println("save");
        lectureService.save(lecture);
    }
}
