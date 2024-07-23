package com.ups.oop.service;

import com.ups.oop.dto.StudentDTO;
import com.ups.oop.entity.Student;
import com.ups.oop.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private List<StudentDTO> studentDTOList = new ArrayList<>();

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public ResponseEntity createStudent(StudentDTO studentDTO) {
        String studentId = studentDTO.getId();
        Optional<Student> studentOptional = studentRepository.findByStudentId(StudentId);
        if (studentOptional.isPresent()) {
            String errorMessage = "student with id " + studentDTO.getId() + " already exists";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        } else {
            if (StudentDTO.getName().contains(" ")) {
                Student studentRecord = new Student();
                studentRecord.setStudentId(studentId);
                String[] nameStrings = studentDTO.getName().split(" ");
                String name = nameStrings[0];
                String lastname = nameStrings[1];
                studentRecord.setName(name);
                studentRecord.setLastname(lastname);
                studentRepository.save(studentRecord);
                return ResponseEntity.status(HttpStatus.OK).body(studentDTO);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("student name must contain two strings separated by a whitespace");

            }
        }
    }
    public ResponseEntity getAllPeople() {
        Iterable<Student> studentIterable = studentRepository.findAll();
        List<StudentDTO> peopleList = new ArrayList<>();

        for (Student s : studentIterable) {
            StudentDTO student = new StudentDTO(s.getStudentId(), s.getName() + "-" + s.getLastname());
            peopleList.add(student);
        }
    }
}