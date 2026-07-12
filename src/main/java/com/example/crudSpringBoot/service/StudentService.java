package com.example.crudSpringBoot.service;

import com.example.crudSpringBoot.dto.StudentRequestDto;
import com.example.crudSpringBoot.dto.StudentResponseDto;
import com.example.crudSpringBoot.dto.UpdateRequestDto;
import com.example.crudSpringBoot.dto.UpdateResponseDto;
import com.example.crudSpringBoot.entity.Student;
import com.example.crudSpringBoot.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentResponseDto createStudent(StudentRequestDto studentReq){

        //performs business logic(like validations and other tasks on the request) and saves the student to the database
        Student savedStudent= mapToEntity(studentReq);
        savedStudent.setCreatedAt(LocalDateTime.now());
        savedStudent.setUpdatedAt(LocalDateTime.now());
        Student studentResp=studentRepository.save(savedStudent);//returns the same object that is saved in DB.

        StudentResponseDto studentRespDto=mapToDto(studentResp);
        return studentRespDto;
    }

    public Student getStudent(Long id){
        Optional<Student> student=studentRepository.findByIdAndDeletedIsFalse(id);
        if(student.isPresent()){
            return student.get();
        }

        return null;
    }

    public List<Student> getAllStudents(){
       List<Student> student=studentRepository.findByAndDeletedIsFalse();
       if(!student.isEmpty()){
           return student;
       }
       return null;
    }

    public UpdateResponseDto updateStudent(Long id, UpdateRequestDto studentReq){
        Optional<Student> existingStudent=studentRepository.findByIdAndDeletedIsFalse(id);
        if(existingStudent.isEmpty()){
            return null;
        }

        Student studentToSave=existingStudent.get();
        studentToSave.setAge(studentReq.getAge());
        studentToSave.setName(studentReq.getName());
        studentToSave.setRollNumber(studentReq.getRollNumber());
        studentToSave.setSubject(studentReq.getSubject());
        studentToSave.setDeleted(false);
        studentToSave.setUpdatedAt(LocalDateTime.now());
        Student savedStudent= studentRepository.save(studentToSave);
        UpdateResponseDto studentRespDto=mapToUpdateDto(savedStudent);
        return studentRespDto;
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

    public boolean softDeleteStudent(Long id){
        Optional<Student> existingStudent=studentRepository.findById(id);
        if(existingStudent.isEmpty()){
            return false;
        }
        Student toDeleteStudent=existingStudent.get();
        toDeleteStudent.setDeleted(true);
        studentRepository.save(toDeleteStudent);
        return true;
    }

    private Student mapToEntity(StudentRequestDto studentReq){

        Student student=new Student();
        student.setName(studentReq.getName());
        student.setAge(studentReq.getAge());
        student.setEmail(studentReq.getEmail());
        student.setRollNumber(studentReq.getRollNumber());
        student.setSubject(studentReq.getSubject());
        student.setDeleted(false);
        return student;
    }

    private StudentResponseDto mapToDto(Student student){
        StudentResponseDto studentRespDto=new StudentResponseDto();
        studentRespDto.setId(student.getId());
        studentRespDto.setName(student.getName());
        studentRespDto.setAge(student.getAge());
        studentRespDto.setEmail(student.getEmail());
        studentRespDto.setSubject(student.getSubject());
        studentRespDto.setRollNumber(student.getRollNumber());
        studentRespDto.setCreatedAt(student.getCreatedAt());
        studentRespDto.setUpdatedAt(student.getUpdatedAt());

        return studentRespDto;
    }

    private UpdateResponseDto mapToUpdateDto(Student student){
        UpdateResponseDto studentRespDto=new UpdateResponseDto();

        studentRespDto.setAge(student.getAge());
        studentRespDto.setName(student.getName());
        studentRespDto.setRollNumber(student.getRollNumber());
        studentRespDto.setSubject(student.getSubject());
        studentRespDto.setEmail(student.getEmail());
        studentRespDto.setUpdatedAt(student.getUpdatedAt());
        return studentRespDto;
    }
}
