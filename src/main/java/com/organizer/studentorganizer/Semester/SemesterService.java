package com.organizer.studentorganizer.Semester;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SemesterService {

    private final SemesterRepository semesterRepository;

    @Autowired
    public SemesterService(SemesterRepository semesterRepository) {
        this.semesterRepository = semesterRepository;
    }


    public Semester addSemester(Semester sem) {
        semesterRepository.save(sem);
        return sem;
    }

}
