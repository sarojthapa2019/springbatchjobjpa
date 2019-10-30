package edu.mum.ea.springbatchjobjpa.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;
    private String lastName;
    private String cgpa;
    private String birthDate;

    public Student(){}
    public Student(String firstName, String lastName, String cgpa, String birthDate){
        this.firstName = firstName;
        this.lastName = lastName;
        this.cgpa = cgpa;
        this.birthDate = birthDate;
    }
}
