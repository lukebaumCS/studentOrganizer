package com.organizer.studentorganizer.Course;

import com.organizer.studentorganizer.Professor.Professor;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
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


    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseEvent> events = new ArrayList<>();

    @NotNull(message = "Credits is required")
    private Integer credits;

    private LocalDate examDateOne;
    private LocalDate examDateTwo;


    public Course() {
    }

    public Course(String name, String description, Integer credits, LocalDate examDateOne, LocalDate examDateTwo, List<CourseEvent> events) {
        this.name = name;
        this.description = description;
        this.credits = credits;
        this.examDateOne = examDateOne;
        this.examDateTwo = examDateTwo;
        this.events = events;
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

    public List<CourseEvent> getEvents() { return events; }

    public void setEvents(List<CourseEvent> events) { this.events = events; }
}

