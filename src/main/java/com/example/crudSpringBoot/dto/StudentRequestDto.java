package com.example.crudSpringBoot.dto;

import jakarta.validation.constraints.*;

public class StudentRequestDto {

    @NotBlank(message = "name cannot be null empty or whitespaces")
    @Size(min = 3, max = 20, message = "name must be between 3 and 20 characters")
    private String name;

    @Min(value=18)
    private int age;

    @Email
    private String email;

    @NotEmpty
    private int rollNumber;

    @NotEmpty
    private String subject;


    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}
