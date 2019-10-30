package edu.mum.ea.springbatchjobjpa.repository;

import edu.mum.ea.springbatchjobjpa.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
