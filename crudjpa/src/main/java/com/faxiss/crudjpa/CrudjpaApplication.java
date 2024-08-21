package com.faxiss.crudjpa;

import com.faxiss.crudjpa.dao.StudentDAO;
import com.faxiss.crudjpa.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CrudjpaApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(CrudjpaApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
			deleteStudent(studentDAO);
		};
	}

	private void deleteStudent(StudentDAO studentDAO) {
		System.out.println("Deleting student with ID = 1");
		studentDAO.delete(1);
	}

	private void createStudent(StudentDAO studentDAO) {

		System.out.println("Creating new student object...");

		Student student = new Student("furqan.khan1836@gmail.com", "Furqan", "Naeem");

		System.out.println("Created object --->"+student.toString());
		System.out.println("Saving the object in DB");

		studentDAO.saveStudent(student);

		System.out.println("Saved student with ID "+student.getId()+" in the DB");
	}

	private void readStudentById(StudentDAO studentDAO, int id){
		System.out.println("Reading student with ID "+id+" from DB");
        Student theStudent = studentDAO.findById(id);
		System.out.println("Student: "+theStudent.toString());
	}

	public void readAllStudents(StudentDAO studentDAO){
		System.out.println("Reading all students from DB");
        List<Student> theStudents = studentDAO.findAll();

		for(Student student : theStudents){
        	System.out.println(student);
	}
	}

	public void getStudentsByLastName(StudentDAO studentDAO){
		System.out.println("Reading students with last name 'Naeem' from DB");
        List<Student> theStudents = studentDAO.findByLastName("Naeem");

        for(Student student : theStudents){
            System.out.println(student);
        }

	}

	public void updateStudent(StudentDAO studentDAO){
		int id = 1;
		System.out.println("Updating student with ID "+id+" in DB");
		Student theStudent = studentDAO.findById(id);
		theStudent.setFirstName("Farhan Updated");
		studentDAO.update(theStudent);
	}


}
