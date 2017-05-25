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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

@Controller
@RequestMapping("/admin/teacherPage")
public class TeacherController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentGroupRepository studentGroupRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView teachersList() {
        ModelMap model = new ModelMap();
        model.addAttribute("teachers",
                userRepository.findByRoles_name("ROLE_TEACHER"));
        return new ModelAndView("admin_teacher_list", model);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addTeacherView() {
        return new ModelAndView("admin_add_teacher", "user", new User());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addTeacher(@ModelAttribute User teacher) {
        if (userRepository.findByUsername(teacher.getUsername()) == null) {
            teacher.setEnabled(true);
            teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
            teacher.setRoles(Collections.singletonList(roleRepository.findByName("ROLE_TEACHER")));
            userRepository.save(teacher);
            return "redirect:/admin/teacherPage";
        }
        return "";
    }

    @RequestMapping(value = "/{teacherId}/edit", method = RequestMethod.GET)
    public ModelAndView editTeacherView(@PathVariable Long teacherId) {
        return new ModelAndView("admin_edit_teacher",
                "user",
                userRepository.findOne(teacherId));
    }

    @RequestMapping(value = "/{teacherId}/edit", method = RequestMethod.POST)
    public String editTeacher(@PathVariable Long teacherId,
                              @RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName,
                              @RequestParam("username") String username) {
        User teacher = userRepository.findOne(teacherId);
        if (teacher.getUsername().equals(username) ||
                userRepository.findByUsername(username) == null) {
            teacher.setUsername(username);
            teacher.setFirstName(firstName);
            teacher.setLastName(lastName);
            userRepository.save(teacher);
            return "redirect:/admin/teacherPage";
        }
        return "redirect:/admin/teacherPage/{teacherId}/edit";
    }

    @RequestMapping(value = "/{teacherId}/delete", method = RequestMethod.POST)
    public String deleteTeacher(@PathVariable Long teacherId) {
        userRepository.delete(teacherId);
        return "redirect:/admin/teacherPage";
    }
}
