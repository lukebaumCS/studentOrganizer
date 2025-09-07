package com.organizer.studentorganizer.Semester;

import com.organizer.studentorganizer.Course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Long> {
    List<Semester> getSemestersByStartYear(Integer startYear);
}

