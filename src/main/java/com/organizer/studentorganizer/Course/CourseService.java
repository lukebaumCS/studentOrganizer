package com.organizer.studentorganizer.Course;

import com.organizer.studentorganizer.Semester.Semester;
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

    public void addCourse(Course course) {
        courserepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courserepository.findAll();
    }

    public Course getCourseById(long id) {
        if (courserepository.existsById(id))
            return courserepository.findById(id).get();
        else return null;
    }

    @Transactional
    public void deleteCourseById(Long id) {
        courserepository.deleteById(id);
    }

    public List<CourseEvent> getAllCourseEvents(Long id) {
        Course course = getCourseById(id);

        if  (course == null) return null;
        else return course.getEvents();
    }

    public Semester getSemesterById(long id) {
        Course course = getCourseById(id);

        if  (course == null) return null;
        else return course.getSemester();
    }

    @Transactional
    public void update(Long existingCourseID, Course updatedCourse) {

        Course existingCourse = this.getCourseById(existingCourseID);

        existingCourse.setName(updatedCourse.getName());
        existingCourse.setDescription(updatedCourse.getDescription());
        existingCourse.setCredits(updatedCourse.getCredits());
        existingCourse.setSemester(updatedCourse.getSemester());


        existingCourse.getEvents().clear();
        for (CourseEvent event : updatedCourse.getEvents()) {
            event.setCourse(existingCourse);
            existingCourse.getEvents().add(event);
        }
        courserepository.delete(updatedCourse);
        courserepository.save(existingCourse);
    }
}
