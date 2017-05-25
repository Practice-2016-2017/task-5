package com.exigen.timetable.controller.admin;

import com.exigen.timetable.pojo.StudentGroup;
import com.exigen.timetable.pojo.User;
import com.exigen.timetable.repository.RoleRepository;
import com.exigen.timetable.repository.StudentGroupRepository;
import com.exigen.timetable.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

@Controller
@RequestMapping("/admin/studentPage")
public class StudentPageController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentGroupRepository studentGroupRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView studentsList() {
        ModelMap model = new ModelMap();
        model.addAttribute("students",
                userRepository.findByRoles_name("ROLE_STUDENT"));
        return new ModelAndView("admin_student_list", model);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addStudentView() {
        ModelMap model = new ModelMap();
        model.addAttribute("user", new User());
        model.addAttribute("studentGroupList", studentGroupRepository.findAll());
        return new ModelAndView("admin_add_student", model);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addStudent(@RequestParam("firstName") String firstName,
                             @RequestParam("lastName") String lastName,
                             @RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam("studentGroup") StudentGroup studentGroup) {
        if (userRepository.findByUsername(username) == null) {
            User student = new User();
            student.setUsername(username);
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setEnabled(true);
            student.setPassword(passwordEncoder.encode(password));
            student.setStudentGroup(studentGroup);
            student.setRoles(Collections.singletonList(roleRepository.findByName("ROLE_STUDENT")));
            userRepository.save(student);
            return "redirect:/admin/studentPage";
        }
        return "";
    }

    @RequestMapping(value = "/{studentId}/edit", method = RequestMethod.GET)
    public ModelAndView editStudentView(@PathVariable Long studentId) {
        ModelMap model = new ModelMap();
        model.addAttribute("user", userRepository.findOne(studentId));
        model.addAttribute("studentGroupList", studentGroupRepository.findAll());
        return new ModelAndView("admin_edit_student", model);
    }

    @RequestMapping(value = "/{studentId}/edit", method = RequestMethod.POST)
    public String editStudent(@PathVariable Long studentId,
                              @RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName,
                              @RequestParam("username") String username,
                              @RequestParam("studentGroup") StudentGroup studentGroup) {
        User student = userRepository.findOne(studentId);
        if (student.getUsername().equals(username) ||
                userRepository.findByUsername(username) == null) {
            student.setUsername(username);
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setStudentGroup(studentGroup);
            userRepository.save(student);
            return "redirect:/admin/studentPage";
        }
        return "redirect:/admin/studentPage/{studentId}/edit";
    }

    @RequestMapping(value = "/{studentId}/delete", method = RequestMethod.POST)
    public String deleteStudent(@PathVariable Long studentId) {
        userRepository.delete(studentId);
        return "redirect:/admin/studentPage";
    }
}
