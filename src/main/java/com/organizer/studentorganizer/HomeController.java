package com.organizer.studentorganizer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String redirectToDashboard() {
        return "redirect:/course/dashboard";
    }
}