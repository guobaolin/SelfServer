package com.gbl.entity;

/**
 * Created by guobaolin on 2019/2/23.
 */
public class Student {
    private Integer studentId;
    private String name;
    private Integer age;
    private String company;

    public Student() {
    }

    public Student(Integer studentId, String name, Integer age, String company) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.company = company;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "[id=" + studentId + ", name=" + name + ",age=" + age + ",company=" + company + "]";
    }
}
