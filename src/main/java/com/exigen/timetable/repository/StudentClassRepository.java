package com.exigen.timetable.repository;

import com.exigen.timetable.pojo.DayOfWeek;
import com.exigen.timetable.pojo.StudentClass;
import com.exigen.timetable.pojo.StudentClassTime;
import com.exigen.timetable.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentClassRepository extends JpaRepository<StudentClass, Long> {
    List<StudentClass> findByDayOfWeek_IdAndTeacher_IdAndStudentClassTime_Id(
            Long dayOfWeekId,
            Long teacherId,
            Long studentClassTimeId);
}
