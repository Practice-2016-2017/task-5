package com.exigen.timetable.controller.student;

import com.exigen.timetable.pojo.DayOfWeek;
import com.exigen.timetable.pojo.StudentClass;
import com.exigen.timetable.pojo.StudentGroup;
import com.exigen.timetable.repository.DayOfWeekRepository;
import com.exigen.timetable.repository.StudentClassRepository;
import com.exigen.timetable.repository.StudentClassTimeRepository;
import com.exigen.timetable.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentClassRepository studentClassRepository;

    @Autowired
    private StudentClassTimeRepository studentClassTimeRepository;

    @Autowired
    private DayOfWeekRepository dayOfWeekRepository;

    @RequestMapping("/timetable")
    public ModelAndView timetable(Authentication authentication) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        StudentGroup studentGroup = userRepository.findByUsername(userDetails.getUsername())
                .getStudentGroup();
        List<StudentClass> studentClasses = studentClassRepository.findByStudentGroup(studentGroup);

        studentClasses.sort((studentClass1, studentClass2) -> {
            Long dayOfWeek1 = studentClass1.getDayOfWeek().getId();
            Long dayOfWeek2 = studentClass2.getDayOfWeek().getId();
            Long studentClassTime1 = studentClass1.getStudentClassTime().getId();
            Long studentClassTime2 = studentClass2.getStudentClassTime().getId();
            if (dayOfWeek1.compareTo(dayOfWeek2) == 0) {
                return studentClassTime1.compareTo(studentClassTime2);
            }
            return dayOfWeek1.compareTo(dayOfWeek2);
        });

        return new ModelAndView("student_timetable", "studentClasses", studentClasses);
    }
}
