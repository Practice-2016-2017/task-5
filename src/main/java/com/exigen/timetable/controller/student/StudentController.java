package com.exigen.timetable.controller.student;

import com.exigen.timetable.repository.StudentClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentClassRepository studentClassRepository;

    @RequestMapping("/timetable")
    public ModelAndView timetable() {
        ModelMap model = new ModelMap();
        model.addAttribute("studentClasses", studentClassRepository.findAll());
        return new ModelAndView("student_timetable", model);
    }
}
