package com.organizer.studentorganizer.Course;


import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
public class CourseEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Enumerated(EnumType.STRING)
    private Weekday weekday;

    private LocalTime startTime;
    private LocalTime endTime;



    public Long getId() { return id; }

    public Course getCourse() { return course; }

    public void setCourse(Course course) { this.course = course; }

    public Type getType() { return type; }

    public void setType(Type type) { this.type = type; }

    public Weekday getWeekday() { return weekday; }

    public void setWeekday(Weekday weekday) { this.weekday = weekday; }

    public LocalTime getStartTime() { return startTime; }

    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }

    public LocalTime getEndTime() { return endTime; }

    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }

}
