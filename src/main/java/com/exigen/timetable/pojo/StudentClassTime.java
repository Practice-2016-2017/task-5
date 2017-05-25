package com.exigen.timetable.pojo;

import javax.persistence.*;
import java.sql.Time;
import java.util.Collection;

@Entity
public class StudentClassTime {
    @Id
    private Long id;

    private Time timeStart;
    private Time timeEnd;

    @OneToMany
    @JoinColumn(name = "student_class_time_id")
    private Collection<StudentClass> studentClasses;

    public StudentClassTime() {

    }

    public StudentClassTime(Long id, Time timeStart, Time timeEnd) {
        this.id = id;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Collection<StudentClass> getStudentClasses() {
        return studentClasses;
    }

    public void setStudentClasses(Collection<StudentClass> studentClasses) {
        this.studentClasses = studentClasses;
    }
}
