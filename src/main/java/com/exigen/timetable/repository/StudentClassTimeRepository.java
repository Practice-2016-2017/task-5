package com.exigen.timetable.repository;

import com.exigen.timetable.pojo.StudentClassTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentClassTimeRepository extends JpaRepository<StudentClassTime, Long> {
    StudentClassTime findById(Long id);
}
