package com.example.crudSpringBoot.service;

import com.example.crudSpringBoot.entity.Student;
import com.example.crudSpringBoot.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student studentReq){
        //performs business logic(like validations and other tasks on the request) and saves the student to the database
        Student savedStudent=studentRepository.save(studentReq);//returns the same object that is saved in DB.
        return savedStudent;
    }

    public Student getStudent(Long id){
        Optional<Student> student=studentRepository.findById(id);
        if(student.isPresent()){
            return student.get();
        }

        return null;
    }

    public List<Student> getAllStudents(){
       List<Student> student=studentRepository.findAll();
       if(!student.isEmpty()){
           return student;
       }
       return null;
    }

    public Student updateStudent(Long id,Student studentReq){
        Optional<Student> existingStudent=studentRepository.findById(id);
        if(existingStudent.isEmpty()){
            return null;
        }

        Student studentToSave=existingStudent.get();
        studentToSave.setAge(studentReq.getAge());
        studentToSave.setEmail(studentReq.getEmail());
        studentToSave.setName(studentReq.getName());
        studentToSave.setRollNumber(studentReq.getRollNumber());
        studentToSave.setSubject(studentReq.getSubject());

        return studentRepository.save(studentToSave);

    }

    public Boolean deleteStudent(Long id){
        Optional<Student> existingStudent=studentRepository.findById(id);
        if(existingStudent.isEmpty()){
            return false;
        }
        Student toDeleteStudent=existingStudent.get();
        studentRepository.delete(toDeleteStudent);

        return true;

    }
}
