package kr.deity.springboot2_x.lecture;

import kr.deity.springboot2_x.lecture.entity.Lecture;
import kr.deity.springboot2_x.lecture.entity.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LectureService {
    final LectureRepository lectureRepository;
    public List<Lecture> list() {
        return lectureRepository.findAll();
    }

    public void save(Lecture lecture) {
        lectureRepository.save(lecture);
    }
}
