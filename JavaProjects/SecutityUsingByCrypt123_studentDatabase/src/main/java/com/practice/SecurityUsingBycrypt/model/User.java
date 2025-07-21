package com.practice.SecurityUsingBycrypt.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="users")
public class User {//The class name I'm using as user, but the table name is users
    @Id
    private  String username;
    private  String password;
}
