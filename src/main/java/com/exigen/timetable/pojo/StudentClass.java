package com.exigen.timetable.pojo;

import javax.persistence.*;

@Entity
public class StudentClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private DayOfWeek dayOfWeek;

    @ManyToOne
    private StudentClassTime studentClassTime;

    @ManyToOne
    private User teacher;

    @ManyToOne
    private StudentGroup studentGroup;

    public StudentClass() {

    }

    public StudentClass(DayOfWeek dayOfWeek,
                        StudentClassTime studentClassTime,
                        User teacher,
                        StudentGroup studentGroup) {
        this.dayOfWeek = dayOfWeek;
        this.studentClassTime = studentClassTime;
        this.teacher = teacher;
        this.studentGroup = studentGroup;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public StudentClassTime getStudentClassTime() {
        return studentClassTime;
    }

    public void setStudentClassTime(StudentClassTime studentClassTime) {
        this.studentClassTime = studentClassTime;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public StudentGroup getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(StudentGroup studentGroup) {
        this.studentGroup = studentGroup;
    }
}
