package com.przemo.demo.student;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.UUID;

public class StudentCourse {

    private final UUID studentId;
    private final UUID courseId;
    private final Date startDate;
    private final Date endDate;
    private int grade;

    public StudentCourse(UUID studentId, UUID courseId, Date startDate, Date endDate, int grade) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.grade = grade;
    }

    public StudentCourse(@JsonProperty("studentId") UUID studentId,
                         @JsonProperty("courseId") UUID courseId,
                         @JsonProperty("startDate") Date startDate,
                         @JsonProperty("endDate") Date endDate) {

        this.studentId = studentId;
        this.courseId = courseId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public UUID getCourseId() {
        return courseId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getGrade() {
        return grade;
    }
}
