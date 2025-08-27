package com.organizer.studentorganizer.Course;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;


    @RequestMapping("/course/dashboard")
    @Transactional
    public String dashboard(Model model) {
        List<Course> allCourses = courseService.getAllCourses();
        model.addAttribute("courses", allCourses);

        return "course/dashboard";

    }


    @RequestMapping("/course/add")
    @Transactional
    public String addCourse() {

        return "course/add";

    }


}
