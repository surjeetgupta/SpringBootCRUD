package com.example.crudSpringBoot.controller;

import com.example.crudSpringBoot.entity.Student;
import com.example.crudSpringBoot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    //@Autowired
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
         Student createdStudent=studentService.createStudent(student);
         //return ResponseEntity.ok(createdStudent);
        //return ResponseEntity.status(201).body(createdStudent);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> student=studentService.getAllStudents();
        if(student==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping("/{id}") //path variable
    public ResponseEntity<Student> getStudentById(@PathVariable Long id){
        Student student=studentService.getStudent(id);
        if(student==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id,@RequestBody Student student){
        Student studentRes=studentService.updateStudent(id,student);
        if(studentRes==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(studentRes);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
        boolean isDeleted=studentService.deleteStudent(id);
       if(!isDeleted) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No records found to delete");
       }
       return ResponseEntity.ok("Student deleted successfully");
    }
}
