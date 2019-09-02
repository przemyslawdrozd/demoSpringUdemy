package com.przemo.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
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

    public Student selectStudentById(UUID studentId) {
        final String sql = "SELECT * FROM student WHERE student_id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{studentId}, mapStudentFromDb());
    }

    int insertStudent(UUID newStudentId, Student newStudent) {
        final String sql = "INSERT INTO student (" +
                "student_id, first_name, last_name, email, gender, age) " +
                "VALUES (?, ?, ?, ?, ?::gender, ?)";

        return jdbcTemplate.update(
                sql,
                newStudentId,
                newStudent.getFirstName(),
                newStudent.getLastName(),
                newStudent.getEmail(),
                newStudent.getGender().name().toUpperCase(),
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

    public List<Course> selectStudentCourseById(UUID student_id) {
        final String sql = "SELECT * FROM student " +
                "JOIN student_course USING (student_id) " +
                "JOIN course USING (course_id) " +
                "WHERE student.student_id = ?";

        return jdbcTemplate.query(
                sql,
                new Object[]{student_id},
                (resultSet, i) -> {
                    UUID courseId = UUID.fromString(resultSet.getString("course_id"));
                    String name = resultSet.getString("name");
                    String description = resultSet.getString("description");
                    String department = resultSet.getString("department");

                    return new Course(courseId, name, description, department);
                });
    }

    public List<StudentCourse> selectStudentCourseInfoById(UUID student_id) {
        final String sql = "SELECT * FROM student " +
                "JOIN student_course USING (student_id) " +
                "WHERE student.student_id = ?";

        return jdbcTemplate.query(sql,
                new Object[] {student_id},
                (resultSet, i) -> {
                    UUID courseId = UUID.fromString(resultSet.getString("course_id"));
                    UUID studentId = UUID.fromString(resultSet.getString("student_id"));
                    Date startDate = resultSet.getDate("start_date");
                    Date endDate = resultSet.getDate("end_date");
                    int grade = Optional.ofNullable(resultSet.getString("grade")).map(Integer::parseInt).orElse(0);
                    return new StudentCourse(studentId, courseId, startDate, endDate, grade);
                });
    }

    @SuppressWarnings("ConstantConditions")
    boolean ifEmailTaken(String email) {
        final String sql = "SELECT EXISTS (" +
                " SELECT 1 " +
                " FROM student" +
                " WHERE email = ?" +
                ")";
        return jdbcTemplate.queryForObject(
                sql,
                new Object[] {email},
                (resultSet, i) -> resultSet.getBoolean(1)
        );
    }
}













