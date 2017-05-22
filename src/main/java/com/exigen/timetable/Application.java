package com.exigen.timetable;

import com.exigen.timetable.pojo.Role;
import com.exigen.timetable.pojo.StudentClass;
import com.exigen.timetable.pojo.StudentGroup;
import com.exigen.timetable.pojo.User;
import com.exigen.timetable.repository.RoleRepository;
import com.exigen.timetable.repository.StudentClassRepository;
import com.exigen.timetable.repository.StudentGroupRepository;
import com.exigen.timetable.repository.UserRepository;
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

		createStudentClassIfNotExist(StudentClass.DayOfWeek.MON);
		createStudentClassIfNotExist(StudentClass.DayOfWeek.THU);
		createStudentClassIfNotExist(StudentClass.DayOfWeek.FRI);
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
					name + "@" + name + ".ru",
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

	private void createStudentClassIfNotExist(StudentClass.DayOfWeek dayOfWeek) {
		StudentClass studentClass = new StudentClass(
				dayOfWeek,
				new Time(36000000),
				new Time(39600000),
				userRepository.findByUsername("teacherTest"),
				studentGroupRepository.findByName("321"));
		studentClassRepository.save(studentClass);
	}
}
