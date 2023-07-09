package com.example.Jpahibernatejuly;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public String addstudent(Student student) {
        studentRepository.save(student);
        return "Student added Successfully" ;
    }

    public Student getStudentById(int rollNo) {
          Optional<Student>optionalStudent=  studentRepository.findById(rollNo);
          if(optionalStudent.isEmpty()){
              return null;
          }
          return optionalStudent.get();
//        Student student= studentRepository.findById(rollNo).orElseThrow(() -> new RuntimeException("Entity not found"));
//        return student;
    }

    public String deleteStudentById(int rollNo) {
        studentRepository.deleteById(rollNo);
        return "Student Hasbeen successfully deleted ";
    }

    public Student updateAgeById(int rollNo , int age) {
       Student student= studentRepository.findById(rollNo).orElseThrow(() -> new RuntimeException("Entity not found"));
        student.setAge(age);
        studentRepository.save(student);
        return student;
    }

    public List<Student> findStudentAgeGraeterThenX(int x) {
        List<Student>studentList = studentRepository.findAll();
        List<Student> students = new ArrayList<>();
        for(Student student: studentList){
            if(student.getAge()>=x){
    students.add(student);
            }
        }
        return students;
    }

    public void deleteStudentAll() {
        studentRepository.deleteAll();
    }
}