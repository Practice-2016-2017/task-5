package com.exigen.timetable.repository;

import com.exigen.timetable.pojo.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
