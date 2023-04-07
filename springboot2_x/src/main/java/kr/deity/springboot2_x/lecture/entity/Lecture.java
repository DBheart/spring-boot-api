package kr.deity.springboot2_x.lecture.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Lecture {
    @Id
    @GeneratedValue
    Long seq;

    String name;
}
