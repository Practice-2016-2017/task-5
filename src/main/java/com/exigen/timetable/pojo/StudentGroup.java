package com.exigen.timetable.pojo;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
public class StudentGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany
    @JoinColumn(name = "student_group_id")
    private Collection<User> students;

    public StudentGroup() {

    }

    public StudentGroup(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<User> getStudents() {
        return students;
    }

    public void setStudents(Collection<User> students) {
        this.students = students;
    }
}
