package com.organizer.studentorganizer.Course;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseService {

    private final CourseRepository courserepository;


    @Autowired
    public CourseService(CourseRepository courserepository) {
        this.courserepository = courserepository;
    }

    public Course addCourse(Course course) {
        courserepository.save(course);
        return course;
    }

    public List<Course> getAllCourses() {
        return courserepository.findAll();
    }

    @Transactional
    public void deleteCourse(Course course) {
        courserepository.delete(course);
    }
}
