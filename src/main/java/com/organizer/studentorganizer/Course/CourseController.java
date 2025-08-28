package com.organizer.studentorganizer.Course;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("/dashboard")
    @Transactional
    public String dashboard(Model model) {
        List<Course> allCourses = courseService.getAllCourses();
        model.addAttribute("courses", allCourses);

        return "course/dashboard";
    }


    @GetMapping("/add")
    @Transactional
    public String addCourse(Model model) {
        model.addAttribute("course", new Course());

        return "course/add";
    }

    @PostMapping("/add/complete")
    @Transactional
    public String validateNewCourse(@Valid @ModelAttribute("course") Course course, BindingResult result, Model model) {

        if (result.hasErrors()) {
            System.out.println("FEHLER");
            return "course/add";
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
