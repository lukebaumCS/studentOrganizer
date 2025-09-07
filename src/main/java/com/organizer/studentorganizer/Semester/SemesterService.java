package com.organizer.studentorganizer.Semester;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.time.Period;

@Component
public class SemesterService {

    private final SemesterRepository semesterRepository;

    @Autowired
    public SemesterService(SemesterRepository semesterRepository) {
        this.semesterRepository = semesterRepository;
    }

    public void addSemester(Semester sem) {
        semesterRepository.save(sem);
    }

    public List<Semester> getAllSemesters() {
        return semesterRepository.findAll();
    }

    public Semester getSemesterById(Long id) {
        return semesterRepository.findById(id).get();
    }

    public Semester getAutomaticSemester() {
        LocalDate today = LocalDate.now();
        int thisYear = today.getYear();
        List<Semester> allSemesters = this.getAllSemesters();

        boolean nextSemester = false;
        for (Semester sem : allSemesters) {
            LocalDate semStartDate =  sem.getStartDate();

            int year =  sem.getStartYear();

            if (year == thisYear) {
                Period period = Period.between(semStartDate, today);
                int monthsSinceStart = period.getMonths();

                // we are max 3 months into the semester or the nextSemester has been set to true
                if (monthsSinceStart<3 || nextSemester )
                    return sem;

                // We are at the end of a semester (min 3 months) and want to add a course for the next semester
                else
                    nextSemester = true;
            }
        }
        // only be called if no more semesters available
        return allSemesters.getLast();
    }

}
