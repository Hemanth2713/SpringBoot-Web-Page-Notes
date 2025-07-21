package com.practice.jdbcexmpl;

import com.practice.jdbcexmpl.model.Student;
import com.practice.jdbcexmpl.service.StudentService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

@SpringBootApplication
public class JdbcexmplApplication {

	public static void main(String[] args) {
		ApplicationContext context =SpringApplication.run(JdbcexmplApplication.class, args);
		Student s1=new Student();
		s1.setRollno(205);
		s1.setName("Charan");
		s1.setMarks(1234);

		StudentService service=context.getBean(StudentService.class);
		service.addStudent(s1);

		List<Student> students=service.getStudents();
		System.out.println(students);
	}

}