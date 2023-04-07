package kr.deity.springboot3_notsecurity.lecture.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
