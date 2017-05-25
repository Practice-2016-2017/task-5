package com.exigen.timetable.controller.admin;

import com.exigen.timetable.pojo.*;
import com.exigen.timetable.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/studentClassPage")
public class StudentClassPageController {

    @Autowired
    private StudentClassRepository studentClassRepository;

    @Autowired
    private StudentGroupRepository studentGroupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentClassTimeRepository studentClassTimeRepository;

    @Autowired
    private DayOfWeekRepository dayOfWeekRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView studentClassList() {
        return new ModelAndView(
                "admin_student_class_list",
                "studentClasses",
                studentClassRepository.findAll());
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addStudentClassView() {
        ModelMap model = new ModelMap();
        model.addAttribute("studentClass", new StudentClass());
        model.addAttribute("dayOfWeekList", dayOfWeekRepository.findAll());
        model.addAttribute("studentGroupList", studentGroupRepository.findAll());
        model.addAttribute("teacherList", userRepository.findByRoles_name("ROLE_TEACHER"));
        model.addAttribute("studentClassTimeList", studentClassTimeRepository.findAll());
        return new ModelAndView("admin_add_student_class", model);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addStudentClass(@ModelAttribute StudentClass studentClass)  {
        studentClassRepository.save(studentClass);
        return "redirect:/admin/studentClassPage";

    }

    @RequestMapping(value = "/{studentClassId}/edit", method = RequestMethod.GET)
    public ModelAndView editStudentClassView(@PathVariable("studentClassId") Long studentClassId) {
        ModelMap model = new ModelMap();
        model.addAttribute("studentClass", studentClassRepository.findOne(studentClassId));
        model.addAttribute("dayOfWeekList", dayOfWeekRepository.findAll());
        model.addAttribute("studentGroupList", studentGroupRepository.findAll());
        model.addAttribute("teacherList", userRepository.findByRoles_name("ROLE_TEACHER"));
        model.addAttribute("studentClassTimeList", studentClassTimeRepository.findAll());
        return new ModelAndView("admin_edit_student_class", model);
    }

    @RequestMapping(value = "/{studentClassId}/edit", method = RequestMethod.POST)
    public String editStudentClass(@ModelAttribute StudentClass studentClass) {
        studentClassRepository.save(studentClass);
        return "redirect:/admin/studentClassPage";
    }

    @RequestMapping(value = "/{studentClassId}/delete", method = RequestMethod.POST)
    public String deleteStudentClass(@PathVariable("studentClassId") Long studentClassId) {
        studentClassRepository.delete(studentClassId);
        return "redirect:/admin/studentClassPage";
    }
}
