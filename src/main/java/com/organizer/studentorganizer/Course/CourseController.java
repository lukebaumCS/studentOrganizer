package com.organizer.studentorganizer.Course;

import com.organizer.studentorganizer.Semester.Semester;
import com.organizer.studentorganizer.Semester.SemesterService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private SemesterService semesterService;


    @RequestMapping("/dashboard")
    @Transactional
    public String dashboard(Model model) {
        List<Course> allCourses = courseService.getAllCourses();
        model.addAttribute("courses", allCourses);

        return "course/dashboard";
    }


    @RequestMapping("/mainpage/{id}")
    public String mainpage(@PathVariable Long id, Model model) {
        Course course = courseService.getCourseById(id);
        List<CourseEvent> events = courseService.getAllCourseEvents(id);

        System.out.println(events);

        model.addAttribute("course", course);
        model.addAttribute("events", events);

        return "course/mainpage";
    }


    @GetMapping("/add")
    @Transactional
    public String addCourse(Model model) {
        if (!model.containsAttribute("course")) {
            Course course = new Course();

            List<CourseEvent> events = new ArrayList<>();
            events.add(new CourseEvent());
            course.setEvents(events);

            List<Semester> allSemester = semesterService.getAllSemester();

            model.addAttribute("course", course);
            model.addAttribute("allSemesters", allSemester);
        }
        return "course/newCourse";
    }


    @PostMapping("/add/event")
    @Transactional
    public String addEvent(@ModelAttribute("course") Course course, Model model) {
        if (course.getEvents() == null) {
            course.setEvents(new ArrayList<>());
        }

        List<Semester> allSemester = semesterService.getAllSemester();
        course.getEvents().add(new CourseEvent());

        model.addAttribute("allSemesters", allSemester);
        model.addAttribute("course", course);

        return "course/newCourse";
    }


    @PostMapping("/add/complete")
    @Transactional
    public String saveNewCourse (@Valid @ModelAttribute("course") Course course, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println("ERROR while creating a new course!");
            return "course/add";
        }


        if (course.getSemester() != null) {
            Semester semester = semesterService.getSemesterById(course.getSemester().getId());
            course.setSemester(semester);
        }

        for (CourseEvent event : course.getEvents()) {
            event.setCourse(course);
        }


        courseService.addCourse(course);
        return "redirect:/course/dashboard";
    }


    @PostMapping("/delete/{id}")
    @Transactional
    public String deleteCourse(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        courseService.deleteCourseById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Course deleted successfully!");

        return "redirect:/course/dashboard";
    }


}
