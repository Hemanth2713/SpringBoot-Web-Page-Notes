package com.practice.SpringJPA;

import com.practice.SpringJPA.model.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Optional;

@SpringBootApplication
public class SpringJpaApplication {
		/*
    I want my application to automatically create a table based on this entity class.
    Spring uses Hibernate behind the scenes, and Hibernate will look for the entity and automatically generate
    the corresponding table in the database if it's not already present.

    To enable this functionality, we need to configure it in the application.properties file:

    spring.jpa.hibernate.ddl-auto=create
    (If we mention 'create' here, it will drop and recreate the table every time the application is run.)

    spring.jpa.hibernate.ddl-auto=update
    (If the table doesn't exist, it will create it, but if the table is already present, it will only update it.)
*/

	public static void main(String[] args) {
		ApplicationContext context =SpringApplication.run(SpringJpaApplication.class, args);
		//We need a object for repository as well.
		StudentRepo repo=context.getBean(StudentRepo.class);

		Student s1=context.getBean(Student.class);
		Student s2=context.getBean(Student.class);
		Student s3=context.getBean(Student.class);

//		s1.setRollNo(101);
//		s1.setName("Sai");
//		s1.setMarks(85.67);
//
//		s2.setRollNo(102);
//		s2.setName("Hemanth");
//		s2.setMarks(90.05);
//
//		s3.setRollNo(103);
//		s3.setName("Durga");
//		s3.setMarks(93.24);
//
//		repo.save(s2);
//		repo.save(s3);

		System.out.println(repo.findAll());
		//By default, we have a inbuilt method called find all(). It should fetch all the records.

		//System.out.println(repo.findById(102));
/*
    findById is a special method that retrieves a row based on the primary key.
    In this case, we are using rollNo as the primary key to fetch the student record.
    If the record is not found, it will return an empty Optional.
*/

		/*
    Optional is used to handle the case where a value may be absent.
    If the record with rollNo 104 is not found in the database, an empty Optional will be returned,
    allowing you to handle the absence of the value safely without encountering a null pointer exception.
*/
		Optional<Student> s = repo.findById(104);
		System.out.println(s.orElse(new Student()));//If the data is not there maybe I want to return a blank student
		//If the data is there it will give you a value otherwise it will give you a blank object.

		/*
		In JPA, there is no built-in method to find data by name.We need to create a custom method in
		the repository to achieve this.
		*/

		System.out.println(repo.findByName("Hemanth"));
		System.out.println(repo.findByMarks(93.24));
		System.out.println(repo.findByMarksGreaterThan(90));
		System.out.println(repo.findByMarksLessThan(90));

		s2.setRollNo(102);
		s2.setName("Hemanth");
		s2.setMarks(92.27);
		//I want to update the student details
		repo.save(s2);//There is no updated method in JPA. We can use the save to update also.

		//If we want to delete the student
		repo.delete(s2);

	}

}
