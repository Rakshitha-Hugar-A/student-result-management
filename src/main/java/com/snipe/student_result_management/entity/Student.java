package com.snipe.student_result_management.entity;

import jakarta.persistence.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "students")
@JsonIgnoreProperties({"marks"})
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    @Column(name = "roll_number", nullable = false, unique = true)
    private String rollNo;

    @Column(name = "student_name", nullable = false)
    private String name;

    @Column(name = "course")
    private String course;
    
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Marks> marks = new ArrayList<>();


    // Default no-arg constructor - required by JPA
    public Student() {}

    // Constructor without id (recommended)
    public Student(String rollNo, String name, String course,String email) {
        this.rollNo = rollNo;
        this.name = name;
        this.course = course;
        this.email = email;
    }

    // Optional constructor with id (less commonly used)
    public Student(Long id, String rollNo, String name, String course,String email) {
        this.id = id;
        this.rollNo = rollNo;
        this.name = name;
        this.course = course;
        this.email = email;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public List<Marks> getMarks() {
        return marks;
    }

    public void setMarks(List<Marks> marks) {
        this.marks = marks;
    }

}
