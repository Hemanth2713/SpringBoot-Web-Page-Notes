package com.practice.SpringJPA;

import com.practice.SpringJPA.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student,Integer> {
    //We got this interface because we have added the library for Spring Data JPA
    //It will ask for 2 things 1.Which class we are working with(Here we are working with Student type of class)
    //2.What is the type of primary key(Every table needs a primary key,In our table we are working with rollno primary key of type integer)

    /*
    We have to write a query. We can use the @Query annotation to specify the query.
    Since we are using JPA, we have a special type of query called JPQL (Java Persistence Query Language).
    It looks similar to SQL queries, but in SQL, we use table and column names.
    In JPQL, we use the class names and the property (field) names instead of table and column names.
*/
    @Query("select s from Student s where s.name= ?1") //This ? replaced by name
/*
    But then you might have multiple question marks, right?
    Let's say you have a complex query with 2 or 3 question marks.
    How do you differentiate which question mark corresponds to which variable?

    In that case, we have to specify numbers to differentiate between the placeholders.
    For example, you can use `?1`, `?2`, `?3`, etc., to bind the values to the corresponding parameters
    in the query. This way, you can clearly map each question mark to a specific variable.
*/

    List<Student> findByName(String name);
    //Here we are returning list format, because multiple may have the same name.

   /*
    Even if we don't explicitly mention the query, it will still work. The thing is, it works, but it won't work for everything.
    JPA uses something called Domain-Specific Language (DSL). Using this DSL, it automatically creates certain methods behind the scenes for you.
    It looks for the column names or property names, such as 'name' and 'marks'.
    JPA will automatically generate queries based on these property names, and you can use those methods directly.

    However, if you specify something custom, like `findBySName`, it won't work because JPA has no idea what "SName" is.
    But JPA does know what 'name' refers to, since it's the name of the property.

    If you need a query that JPA can't generate automatically (such as a more complex query), you'll have to use the `@Query` annotation and specify the query yourself.
    But most of the time, JPA will generate the necessary methods for you automatically.
*/

    List<Student> findByMarks(double marks);

    List<Student> findByMarksGreaterThan(double marks);
    List<Student> findByMarksLessThan(double marks);
}
