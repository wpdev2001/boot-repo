package com.wp.mvc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Student {

    private String name;
    private int empId;
    private String phone;
    @JsonIgnore // This annotation tells to ignore particular field from JSON
    private String dept;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", empId=" + empId +
                ", phone='" + phone + '\'' +
                ", dept='" + dept + '\'' +
                '}';
    }
}
