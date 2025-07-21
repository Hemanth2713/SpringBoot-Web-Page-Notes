package com.practice.SpringJPA.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//We are expecting that the framework should create a table based on this student object. To achieve that we have
// to use Entity
@Scope("prototype")
@Entity
//What happens in the database we treat every table as an entity. The way we can create a table from a class is
// by writing at entity on top of it.
public class Student {
    @Id
    //We are sating @Id, which means let's make the roll number as a primary key.
    private int rollNo;
    private String name;
    private double marks;

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }


    @Override
    public String toString() {
        return "Student{" +
                "rollno=" + rollNo +
                ", name='" + name + '\'' +
                ", marks=" + marks +
                '}';
    }
}
