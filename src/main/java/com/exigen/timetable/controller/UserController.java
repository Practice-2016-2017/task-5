package com.exigen.timetable.controller;

import com.exigen.timetable.pojo.User;
import com.exigen.timetable.repository.RoleRepository;
import com.exigen.timetable.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<?> registration(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()) != null)
            return ResponseEntity.badRequest().build();

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setRoles(Collections.singletonList(roleRepository.findByName("ROLE_STUDENT")));

        userRepository.save(user);

        return ResponseEntity.accepted().build();
    }
}
