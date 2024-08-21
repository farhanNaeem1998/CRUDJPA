package com.faxiss.crudjpa.dao;

import com.faxiss.crudjpa.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{

    private EntityManager entityManager;

    @Autowired
    public StudentDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void saveStudent(Student theStudent){
        entityManager.persist(theStudent);
    }
    @Override
    public Student findById(int id){
        return entityManager.find(Student.class,id);
    }
    @Override
    public List<Student> findAll(){

        String sql = "SELECT * FROM student";
        Query query = entityManager.createNativeQuery(sql, Student.class);
        List<Student> students = query.getResultList();

        return students;
    }

    public List<Student> findByLastName(String lastName){
        String sql = "SELECT * FROM student WHERE last_name =?1";
        Query query = entityManager.createNativeQuery(sql, Student.class);
        query.setParameter(1, lastName);
        List<Student> students = query.getResultList();

        return students;
    }
    @Override
    @Transactional
    public void update(Student theStudent){
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Student student = entityManager.find(Student.class,id);

        entityManager.remove(student);
    }
}
