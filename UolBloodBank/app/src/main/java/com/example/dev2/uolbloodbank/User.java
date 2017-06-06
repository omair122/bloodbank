package com.example.dev2.uolbloodbank;

/**
 * Created by dev2 on 6/2/2017.
 */

public class User {
    int id;
    String name;
    String email;
    String pass;
    String BloodGroup;
    String Phone;


    public User(int id, String name, String email, String pass, String bloodGroup, String Phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.BloodGroup = bloodGroup;
        this.Phone = Phone;

    }

    public User(String s, String s1, String s2, String s3, String s4) {
        this.name = s;
        this.email = s1;
        this.pass = s2;
        this.BloodGroup = s3;
        this.Phone = s4;
    }

    public User() {
        //
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getBloodGroup() {
        return BloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        BloodGroup = bloodGroup;
    }
}
