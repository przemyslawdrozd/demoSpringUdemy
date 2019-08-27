package com.przemo.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class StudentDataAccessService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Student> selectAllStudents() {
        final String sql = "SELECT student_id, first_name, last_name, email, gender, age " +
                "FROM student";

        return jdbcTemplate.query(
                sql,
                mapStudentFromDb());
    }

    int insertStudent(UUID newStudentId, Student newStudent) {
        final String sql = "INSERT INTO student (" +
                "student_id, first_name, last_name, email, gender, age) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(
                sql,
                newStudentId,
                newStudent.getFirstName(),
                newStudent.getLastName(),
                newStudent.getEmail(),
                newStudent.getGender().name(),
                newStudent.getAge()
        );
    }

    private RowMapper<Student> mapStudentFromDb() {
        return (resultSet, i) -> {
            String studentIdSt = resultSet.getString("student_id");
            UUID studentId = UUID.fromString(studentIdSt);

            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String email = resultSet.getString("email");

            String genderStr = resultSet.getString("gender").toUpperCase();
            Student.Gender gender = Student.Gender.valueOf(genderStr);

            int age = resultSet.getInt("age");

            return new Student(
                    studentId,
                    firstName,
                    lastName,
                    email,
                    gender,
                    age);
        };
    }
}