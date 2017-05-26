package com.exigen.timetable.repository;

import com.exigen.timetable.pojo.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentClassRepository extends JpaRepository<StudentClass, Long> {
    List<StudentClass> findByStudentGroup(StudentGroup studentGroup);
}
