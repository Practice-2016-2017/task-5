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

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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

        List<User> students = new LinkedList<>();
        List<String> studentsGroup = new LinkedList<>();
        for (StudentGroup studentGroup : studentGroupRepository.findAll()) {
            students.addAll(studentGroup.getStudents());
            String[] currentStudentGroup = new String[studentGroup.getStudents().size()];
            Arrays.fill(currentStudentGroup, studentGroup.getName());
            studentsGroup.addAll(Arrays.asList(currentStudentGroup));
        }

        model.addAttribute("students", students);
        model.addAttribute("studentsGroup", studentsGroup);
        return new ModelAndView("admin_student_list", model);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addStudentView() {
        return new ModelAndView("admin_add_student");
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addStudent(@RequestParam("firstName") String firstName,
                             @RequestParam("lastName") String lastName,
                             @RequestParam("username") String username,
                             @RequestParam("password") String password) {
        if (userRepository.findByUsername(username) == null) {
            User student = new User();
            student.setUsername(username);
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setEnabled(true);
            student.setPassword(passwordEncoder.encode(password));
            student.setRoles(Collections.singletonList(roleRepository.findByName("ROLE_STUDENT")));
            userRepository.save(student);
            return "redirect:/admin/studentPage";
        }
        return "";
    }

    @RequestMapping(value = "/{studentId}/edit", method = RequestMethod.GET)
    public ModelAndView editStudentView(@PathVariable Long studentId) {
        ModelMap model = new ModelMap();
        model.addAttribute("student", userRepository.findOne(studentId));
        return new ModelAndView("admin_edit_student", model);
    }

    @RequestMapping(value = "/{studentId}/edit", method = RequestMethod.POST)
    public String editStudent(@PathVariable Long studentId,
                              @RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName,
                              @RequestParam("username") String username) {
        User student = userRepository.findOne(studentId);
        if (student.getUsername().equals(username) ||
                userRepository.findByUsername(username) == null) {
            student.setUsername(username);
            student.setFirstName(firstName);
            student.setLastName(lastName);
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
