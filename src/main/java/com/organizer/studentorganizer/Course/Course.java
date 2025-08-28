package com.organizer.studentorganizer.Course;

import com.organizer.studentorganizer.Professor.Professor;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    @ManyToMany(mappedBy = "courses")
    private List<Professor> professor;

    @NotNull(message = "Credits is required")
    private Integer credits;

    private LocalDate examDateOne;
    private LocalDate examDateTwo;

    

    public Course() {}

    public Course(String name, String description, Integer credits, LocalDate examDateOne, LocalDate examDateTwo) {
        this.name = name;
        this.description = description;
        this.credits = credits;
        this.examDateOne = examDateOne;
        this.examDateTwo = examDateTwo;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public LocalDate getExamDateOne() {
        return examDateOne;
    }

    public void setExamDateOne(LocalDate examDateOne) {
        this.examDateOne = examDateOne;
    }

    public LocalDate getExamDateTwo() {
        return examDateTwo;
    }

    public void setExamDateTwo(LocalDate examDateTwo) {
        this.examDateTwo = examDateTwo;
    }
}
