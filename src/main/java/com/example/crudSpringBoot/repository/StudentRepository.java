package com.example.crudSpringBoot.repository;

import com.example.crudSpringBoot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository //bean of this interface will not be created as it is an interface.
public interface StudentRepository extends JpaRepository<Student, Long> {



}
