package edu.mum.ea.springbatchjobjpa.utility;

import edu.mum.ea.springbatchjobjpa.domain.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class StudentProcessor implements ItemProcessor<Student, Student> {
    private static final Logger log = LoggerFactory.getLogger(StudentProcessor.class);
    @Override
    public Student process(Student student) throws Exception {
        final String firstName = student.getFirstName();
        final String lastName = student.getLastName();
        final Double cgpa = student.getCgpa();
         Integer birthDate = Integer.parseInt(student.getBirthDate());
        LocalDate localDate = LocalDate.of(2019-birthDate,1,1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedString = localDate.format(formatter);
        final Student newStudent = new Student(firstName, lastName, cgpa, formattedString);
        log.info("input ("+student +") into ("+ newStudent+ ")" );
        return newStudent;
    }
}
