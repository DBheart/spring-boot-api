package kr.deity.springboot3_notsecurity.lecture;

import kr.deity.springboot3_notsecurity.lecture.entity.Lecture;
import kr.deity.springboot3_notsecurity.lecture.entity.LectureRepository;
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
}
