package com.Hemanth.myfirstproject.Entity;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentEntry {
    private  int studID;

    public long getStudID() {
        return studID;
    }

    public void setStudID(int studID) {
        this.studID = studID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private  String name;
    private  String email;
}