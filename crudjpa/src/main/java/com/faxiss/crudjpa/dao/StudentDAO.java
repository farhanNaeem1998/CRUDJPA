package com.faxiss.crudjpa.dao;

import com.faxiss.crudjpa.entity.Student;

import java.util.List;

public interface StudentDAO {
    void saveStudent(Student theStudent);
    Student findById(int id);
    List<Student> findAll();
    List<Student> findByLastName(String lastName);
    void update(Student theStudent);
    void delete(int id);
}
