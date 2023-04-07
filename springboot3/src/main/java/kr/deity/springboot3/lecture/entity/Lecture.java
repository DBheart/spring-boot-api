package kr.deity.springboot3.lecture.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;

@Data
@Entity
public class Lecture {
    @Id @GeneratedValue
    Long seq;

    String name;

}
