package com.exigen.timetable.repository;

import com.exigen.timetable.pojo.StudentGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentGroupRepository extends JpaRepository<StudentGroup, Long> {
    StudentGroup findByName(String name);
}
