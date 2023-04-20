package kr.deity.restdocwithoutsecurity.lecture.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture,Long> {
}
