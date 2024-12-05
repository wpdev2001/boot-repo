package com.wp.mvc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Student {

    private String name;
    private int rno;
    private String phone;
    private String dept;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRno() {
        return rno;
    }

    public void setRno(int rno) {
        this.rno = rno;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /*This will ignore the value of that field while getting the data from java object,
     displayed as JSON on browser*/
    @JsonIgnore
    public String getDept() {
        return dept;
    }

    //It will set the value while passing data from the JSON to java object
    @JsonProperty
    public void setDept(String dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", rno=" + rno +
                ", phone='" + phone + '\'' +
                ", dept='" + dept + '\'' +
                '}';
    }
}
