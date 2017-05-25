package com.exigen.timetable;

import com.exigen.timetable.pojo.*;
import com.exigen.timetable.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Time;
import java.util.Collections;

@SpringBootApplication
public class Application extends SpringBootServletInitializer implements ApplicationRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private StudentGroupRepository studentGroupRepository;

	@Autowired
	private StudentClassRepository studentClassRepository;

	@Autowired
	private StudentClassTimeRepository studentClassTimeRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		createRoleIfNotExist("ROLE_ADMIN");
		createRoleIfNotExist("ROLE_STUDENT");
		createRoleIfNotExist("ROLE_TEACHER");

		createTestUserIfNotExist("adminTest", "ROLE_ADMIN");
		createTestUserIfNotExist("studentTest", "ROLE_STUDENT");
		createTestUserIfNotExist("teacherTest", "ROLE_TEACHER");

		createStudentGroupIfNotExist("321");
		createStudentGroupIfNotExist("322");
		createStudentGroupIfNotExist("323");
		createStudentGroupIfNotExist("324");
	}

	private void createRoleIfNotExist(String name) {
		Role role = roleRepository.findByName(name);
		if (role == null) {
			role = new Role(name);
			roleRepository.save(role);
		}
	}

	private void createTestUserIfNotExist(String name, String role) {
		if (userRepository.findByUsername(name) == null) {
			User user = new User(
					name,
					name,
					name,
					passwordEncoder.encode(name)
			);
			user.setEnabled(true);
			user.setRoles(Collections.singletonList(roleRepository.findByName(role)));
			userRepository.save(user);
		}
	}

	private void createStudentGroupIfNotExist(String name) {
		if (studentGroupRepository.findByName(name) == null) {
			StudentGroup studentGroup = new StudentGroup(name);
			studentGroup.setStudents(Collections.singletonList(
					userRepository.findByUsername("studentTest")));
			studentGroupRepository.save(studentGroup);
		}
	}
}
