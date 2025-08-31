package com.organizer.studentorganizer.Semester;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.organizer.studentorganizer.StudentOrganizerApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@RequestMapping("/semester")
public class SemesterController {

    @Autowired
    private SemesterService semesterService;

    @RequestMapping("/add")
    public String addSemester(){
        try (InputStreamReader reader = new InputStreamReader(
                StudentOrganizerApplication.class.getResourceAsStream("/sample.json"))) {

            JsonElement jsonElement = JsonParser.parseReader(reader);

            if (jsonElement.isJsonArray()) {
                JsonArray jsonArray = jsonElement.getAsJsonArray();

                for (JsonElement element : jsonArray) {
                    JsonObject obj = element.getAsJsonObject();

                    String name = obj.has("name") ? obj.get("name").getAsString() : "";
                    int startYear = obj.has("startYear") ? obj.get("startYear").getAsInt() : 0;
                    int endYear = obj.has("endYear") ? obj.get("endYear").getAsInt() : 0;

                    String startString = obj.has("startDate") ? obj.get("startDate").getAsString() : "";
                    String endString = obj.has("endDate") ? obj.get("endDate").getAsString() : "";

                    Date startDate;
                    Date endDate;

                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        startDate = formatter.parse(startString);
                        endDate = formatter.parse(endString);

                        System.out.println("Date object: " + startDate);
                        System.out.println("Date object: " + endDate);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    Semester newSem = new Semester(name, startYear, endYear, startDate, endDate);
                    semesterService.addSemester(newSem);
                }
            } else {
                System.out.println("Die JSON-Datei enthält kein Array.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "course/dashboard";
    }
}
