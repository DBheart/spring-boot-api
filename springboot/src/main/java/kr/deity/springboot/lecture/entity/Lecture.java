package kr.deity.springboot.lecture.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Lecture {
    @Id @GeneratedValue
    Long seq;

    String name;
}
