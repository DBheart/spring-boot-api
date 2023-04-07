package kr.deity.springboot3_notsecurity.lecture.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class Lecture {

    @Id @GeneratedValue
    Long seq;

    String name;
}
