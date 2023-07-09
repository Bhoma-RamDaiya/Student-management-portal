package com.example.Jpahibernatejuly;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;
    @PostMapping("/add")
    public ResponseEntity addstudent( @RequestBody Student student){
       String response =  studentService.addstudent(student);
        return new ResponseEntity(response , HttpStatus.CREATED);
    }
    @GetMapping("/get-student-info")
    public ResponseEntity getStudentById(@RequestParam("id") int rollNo){
        try {
            Student student= studentService.getStudentById(rollNo);
            return new ResponseEntity(student , HttpStatus.FOUND);
        }
      catch (Exception e) {
           return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
      }

    }
    @DeleteMapping("/delete-student")
    public ResponseEntity deleteStudentById(@RequestParam("id") int rollNo){
       String response = studentService.deleteStudentById(rollNo);
       return new ResponseEntity(response,HttpStatus.CREATED);
    }
    @PutMapping("/update-age")
    public ResponseEntity updateAgeById(@RequestParam("id") int rollNo, @RequestParam("age") int age){
        Student student = studentService.updateAgeById(rollNo , age);
        if(student==null) return new ResponseEntity("student is not prsent " , HttpStatus.NOT_FOUND);
        return new ResponseEntity(student , HttpStatus.ACCEPTED);

    }
    @GetMapping("/find-student-age>x")
    public ResponseEntity findStudentAgeGraeterThenX(@RequestParam("X") int X){
        List<Student> students = studentService.findStudentAgeGraeterThenX(X);
        return new ResponseEntity(students,HttpStatus.FOUND) ;
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity deleteStudentAll(){
        studentService.deleteStudentAll();
        return new ResponseEntity("all Student has been deleted" , HttpStatus.ACCEPTED);
    }
}