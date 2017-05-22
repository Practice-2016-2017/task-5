package com.exigen.timetable.pojo;

import javax.persistence.*;
import java.sql.Time;

@Entity
public class StudentClass {
    public enum DayOfWeek {
        MON,
        TUE,
        WED,
        THU,
        FRI,
        SAT,
        SUN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    private Time timeStart;
    private Time timeEnd;

    @ManyToOne
    private User teacher;

    @ManyToOne
    private StudentGroup studentGroup;

    public StudentClass() {

    }

    public StudentClass(DayOfWeek dayOfWeek, Time timeStart, Time timeEnd, User teacher, StudentGroup studentGroup) {
        this.dayOfWeek = dayOfWeek;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
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

    public Time getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Time timeStart) {
        this.timeStart = timeStart;
    }

    public Time getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Time timeEnd) {
        this.timeEnd = timeEnd;
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
