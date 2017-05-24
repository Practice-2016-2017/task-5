package com.exigen.timetable.repository;

import com.exigen.timetable.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByUsername(String username);
    List<User> findByRoles_name(String name);
}
