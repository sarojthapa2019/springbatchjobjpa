package edu.mum.ea.springbatchjobjpa.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class StudentList {
    private List<Student> students;

    public StudentList(){
        students = new ArrayList<>();
    }
}
