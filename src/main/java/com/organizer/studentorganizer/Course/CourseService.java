package com.organizer.studentorganizer.Course;

import com.organizer.studentorganizer.Semester.Semester;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseService {

    private final CourseRepository courserepository;

    private final EventService eventService;


    @Autowired
    public CourseService(CourseRepository courserepository, EventService eventService) {
        this.courserepository = courserepository;
        this.eventService = eventService;
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

    public List<Event> getAllEvents(Long id) {
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

        List<Event> toKeep = new ArrayList<>();
        for (Event event : updatedCourse.getEvents()) {
            if (event.getId() != null) {
                // update existing events
                Event existingEvent = eventService.findEventById(event.getId());
                existingEvent.setType(event.getType());
                existingEvent.setWeekday(event.getWeekday());
                existingEvent.setStartTime(event.getStartTime());
                existingEvent.setEndTime(event.getEndTime());
                toKeep.add(existingEvent);
            } else {
                // add new events
                event.setCourse(existingCourse);
                toKeep.add(event);
            }
        }
        existingCourse.setEvents(toKeep);
    }
}
