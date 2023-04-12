package com.example.restdocwithswagger.lecture.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Lecture {
    @Id @GeneratedValue
    Long seq;

    String name;
}
