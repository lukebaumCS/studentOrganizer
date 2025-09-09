package com.organizer.studentorganizer.Semester;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.opencsv.CSVReader;
import com.organizer.studentorganizer.StudentOrganizerApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/semester")
public class SemesterController {

    @Autowired
    private SemesterService semesterService;

    @RequestMapping("/add")
    public String addSemester(){

        String filePath = "semester.csv";
        try (CSVReader reader = new CSVReader(new InputStreamReader(
                new ClassPathResource("semester.csv").getInputStream()))) {

            String[] line;

            while ((line = reader.readNext()) != null) {
                Semester semester = new Semester();
                semester.setName(line[1]); // Name
                semester.setStartYear(Integer.parseInt(line[2]));
                semester.setEndYear(Integer.parseInt(line[3]));
                semester.setStartDate(LocalDate.parse(line[4])); // "yyyy-MM-dd"
                semester.setEndDate(LocalDate.parse(line[5]));

                semesterService.addSemester(semester);
            }
            System.out.println("CSV import completed successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "course/dashboard";
    }
}
