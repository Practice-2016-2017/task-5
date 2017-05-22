package com.exigen.timetable.repository;

import com.exigen.timetable.pojo.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentClassRepository extends JpaRepository<StudentClass, Long> {
}
