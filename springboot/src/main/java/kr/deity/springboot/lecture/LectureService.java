package kr.deity.springboot.lecture;

import kr.deity.springboot.lecture.entity.Lecture;
import kr.deity.springboot.lecture.entity.LectureRepository;
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

    public void save(Lecture lecture) {
        lectureRepository.save(lecture);
    }
}
