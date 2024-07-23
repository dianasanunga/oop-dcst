package com.ups.oop.repository;


import com.ups.oop.entity.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, Long> {
    Optional<Student> findByStudentId(String StudentId);

}

