package com.example.restdocwithswagger.lecture;

import com.example.restdocwithswagger.lecture.entity.Lecture;
import com.example.restdocwithswagger.lecture.entity.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LectureService {
    final LectureRepository lectureRepository;

    public List<Lecture> list() {
        return lectureRepository.findAll();
    }

    public void save(Lecture lecture) {
        lectureRepository.save(lecture);
    }
}
