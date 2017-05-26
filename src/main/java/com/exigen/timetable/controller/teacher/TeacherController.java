package com.exigen.timetable.controller.teacher;

import com.exigen.timetable.pojo.StudentClass;
import com.exigen.timetable.pojo.User;
import com.exigen.timetable.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/teacher/timetable")
public class TeacherController {

    @Autowired
    private StudentClassRepository studentClassRepository;

    @Autowired
    private DayOfWeekRepository dayOfWeekRepository;

    @Autowired
    private StudentGroupRepository studentGroupRepository;

    @Autowired
    private StudentClassTimeRepository studentClassTimeRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView timetable(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<StudentClass> studentClasses =
                studentClassRepository.findByTeacher_Username(userDetails.getUsername());

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

        return new ModelAndView("teacher_timetable", "studentClasses", studentClasses);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addStudentClassView() {
        ModelMap model = new ModelMap();
        model.addAttribute("studentClass", new StudentClass());
        model.addAttribute("dayOfWeekList", dayOfWeekRepository.findAll());
        model.addAttribute("studentGroupList", studentGroupRepository.findAll());
        model.addAttribute("studentClassTimeList", studentClassTimeRepository.findAll());
        return new ModelAndView("teacher_add_student_class", model);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addStudentClass(@ModelAttribute StudentClass studentClass,
                                  Authentication authentication)  {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        studentClass.setTeacher(userRepository.findByUsername(userDetails.getUsername()));

        studentClassRepository.save(studentClass);
        return "redirect:/teacher/timetable";

    }
}
