package kr.deity.restdocwithoutsecurity.lecture;

import kr.deity.restdocwithoutsecurity.lecture.entity.Lecture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class LectureServiceTest {
    @Autowired
    LectureService lectureService;

    @Test
    @DisplayName("수강 확인")
    public void testList(){
        List<Lecture> list= lectureService.list();
        Assertions.assertThat(list.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("수강 등록")
    public void testRegisterLecture(){
        Lecture lecture = new Lecture();
        lecture.setName("수학");

        lectureService.register(lecture);

        List<Lecture> list = lectureService.list();
        Assertions.assertThat(list.size()).isEqualTo(1);
    }
}