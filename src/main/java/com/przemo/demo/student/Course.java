package com.przemo.demo.student;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class Course {

    private final UUID courseId;

    @NotBlank
    private final String name;

    @NotBlank
    private final String description;

    @NotBlank
    private final String department;

    private String teacher;

    public Course(@JsonProperty("courseId") UUID courseId,
                  @JsonProperty("name") String name,
                  @JsonProperty("description") String description,
                  @JsonProperty("department") String department,
                  @JsonProperty("teacher") String teacher) {

        this.courseId = courseId;
        this.name = name;
        this.description = description;
        this.department = department;
        this.teacher = teacher;
    }

    public Course(@JsonProperty("courseId") UUID courseId,
                  @JsonProperty("name") String name,
                  @JsonProperty("description") String description,
                  @JsonProperty("department") String department) {

        this.courseId = courseId;
        this.name = name;
        this.description = description;
        this.department = department;
    }


    public UUID getCourseId() {
        return courseId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDepartment() {
        return department;
    }

    public String getTeacher() {
        return teacher;
    }
}
