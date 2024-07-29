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

        String studentId = studentDTO.getStudent_code();

        Optional<Student> studentOptional = studentRepository.findByStudentId(studentId);

        if (studentOptional.isPresent()) {
            String errorMessage = "student with id " + studentId + " already exists";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        } else {
            if (studentDTO.getName().contains(" ")) {
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

    public ResponseEntity getAllStudent() {
        Iterable<Student> studentIterable = studentRepository.findAll();
        List<StudentDTO> studentList = new ArrayList<>();

        for (Student s : studentIterable) {
            StudentDTO student = new StudentDTO(s.getStudentId(), s.getName()+ " " + s.getLastname());
            studentList.add(student);
        }

        if(studentList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student List not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(studentList);
    }

    public ResponseEntity getStudentById(String studentId){

        Optional<Student> studentOptional = studentRepository.findByStudentId(studentId);

        if(studentOptional.isPresent()){

            Student studentFound = studentOptional.get();
            StudentDTO student = new StudentDTO(studentFound.getStudentId(),
                    studentFound.getName() + "-" + studentFound.getLastname());
            return ResponseEntity.status(HttpStatus.OK).body(student);
        } else {
            String errorMessage = "student with id " + studentId + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);

        }
    }

    public ResponseEntity updateStudent(StudentDTO studentDTO){

        String studentId = studentDTO.getStudent_code();
        Optional<Student> studentOptional = studentRepository.findByStudentId(studentId);
        if(studentOptional.isPresent()){
            Student studentRecord = studentOptional.get();
            if (studentDTO.getName().contains(" ")) {
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

        }else {
            String errorMessage = "student with id " + studentId + "not fund";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    public ResponseEntity deleteStudentById(String id){
        String message = "student with id " + id;
        Optional<Student> studentOptional = studentRepository.findByStudentId(id);
        if(studentOptional.isPresent()){
            studentRepository.delete(studentOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body(message + " removed successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message + " not found");
    }
}