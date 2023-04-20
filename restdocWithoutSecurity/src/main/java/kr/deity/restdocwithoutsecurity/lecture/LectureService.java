package kr.deity.restdocwithoutsecurity.lecture;

import kr.deity.restdocwithoutsecurity.lecture.entity.Lecture;
import kr.deity.restdocwithoutsecurity.lecture.entity.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureService {

    final LectureRepository lectureRepository;


    public List<Lecture> list() {
        return lectureRepository.findAll();
    }

    public void register(Lecture lecture) {
        lectureRepository.save(lecture);
    }
}
