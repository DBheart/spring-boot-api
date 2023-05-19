package kr.deity.springboot3.lecture;

import kr.deity.springboot3.lecture.entity.Lecture;
import kr.deity.springboot3.lecture.entity.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LectureService {
    final LectureRepository lectureRepository;

    public void save(Lecture lecture) {
        lectureRepository.save(lecture);

        var a = 100_000;
    }

    public List<Lecture> list() {
        return lectureRepository.findAll();
    }
}
