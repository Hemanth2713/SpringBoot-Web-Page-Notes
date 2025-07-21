package com.practice.jdbcexmpl.repository;

import com.practice.jdbcexmpl.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Component
@Repository
public class StudentRepo {
    private JdbcTemplate jdbc;

    public JdbcTemplate getJdbc() {
        return jdbc;
    }

    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    public void save(Student s) {
        String sql="insert into student(rollno,name,marks) values (?,?,?)";
        int rows=jdbc.update(sql,s.getRollno(),s.getName(),s.getMarks());
        System.out.println(rows+"Effected");
    }
    public List<Student> findAll() {
       String sql="select * from student";
       //We can convert new RowMapper object as lambda expression
        RowMapper<Student> mapper=new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {

                //RowMapper will implements the mapRow interface.This interface or method takes 2 parameters.
                //The job of mapRow is to give one row at a time.
                //First parameter --> Result Set
                //Second parameter --> rowNum
                Student s=new Student();
                s.setRollno(rs.getInt("rollno"));
                s.setName(rs.getString("name"));
                s.setMarks(rs.getDouble("marks"));
                //Here we are taking the data rollno,name,marks and we are adding it into the student object ans we are retiurning it.

                return s;
            }
        };
       return jdbc.query(sql,mapper);
       //Query takes 2 parameters first-->SQL Query
       //second--> Object of RowMapper. Using RowMapper we can fetch the data from our result set.
        //RowMapper help us to fetch the one by one row data.
    }
}




