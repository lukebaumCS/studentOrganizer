package com.organizer.studentorganizer.Course;

import com.organizer.studentorganizer.Professor.Professor;
import com.organizer.studentorganizer.Semester.Semester;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToMany(mappedBy = "courses", cascade = CascadeType.ALL)
    private List<Professor> professor;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "semester_id")
    private Semester semester;

    @NotNull(message = "Credits is required")
    private Float credits;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate examDateOne;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate examDateTwo;


    public Course() {
    }

    public Course(String name, String description, Float credits, LocalDate examDateOne, LocalDate examDateTwo, List<Event> events, Semester semester) {
        this.name = name;
        this.description = description;
        this.credits = credits;
        this.examDateOne = examDateOne;
        this.examDateTwo = examDateTwo;
        this.events = events;
        this.semester = semester;
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

    public Float getCredits() {
        return credits;
    }

    public void setCredits(Float credits) {
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

    public List<Event> getEvents() { return events; }

    public void setEvents(List<Event> events) { this.events = events; }

    public Semester getSemester() { return this.semester; }

    public void setSemester(Semester semester) { this.semester = semester; }
}

