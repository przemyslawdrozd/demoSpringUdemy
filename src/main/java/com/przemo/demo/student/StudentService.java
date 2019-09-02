package com.przemo.demo.student;

import com.przemo.demo.EmailValidation;
import com.przemo.demo.exceptions.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentDataAccessService studentDataAccessService;
    private final EmailValidation emailValidation;

    @Autowired
    public StudentService(StudentDataAccessService studentDataAccessService,
                          EmailValidation emailValidation) {
        this.studentDataAccessService = studentDataAccessService;
        this.emailValidation = emailValidation;
    }

    List<Student> getAllStudents() {
        return studentDataAccessService.selectAllStudents();
    }

    void addNewStudent(Student newStudent) {
        addNewStudent(null, newStudent);
    }

    void addNewStudent(UUID studentId, Student newStudent) {
        UUID newStudentId = Optional.ofNullable(studentId).orElse(UUID.randomUUID());

        // TODO: Validate Email
        if (!emailValidation.test(newStudent.getEmail())) {
            throw new ApiRequestException(newStudent.getEmail() + " is not valid!");
        }

        // TODO: Verify that email is not taken
        if (studentDataAccessService.ifEmailTaken(newStudent.getEmail())){
            throw new ApiRequestException(newStudent.getEmail() + " is taken!");
        }
        studentDataAccessService.insertStudent(newStudentId, newStudent);
    }

    public List<Course> getStudentCourseById(String student_id) {
        UUID id = UUID.fromString(student_id);
        return studentDataAccessService.selectStudentCourseById(id);
    }

    public Student getStudentById(String studentId) {
        UUID id = UUID.fromString(studentId);
        return studentDataAccessService.selectStudentById(id);
    }

    public List<StudentCourse> getStudentCourseInfoById(String studentId) {
        UUID id = UUID.fromString(studentId);
        return studentDataAccessService.selectStudentCourseInfoById(id);
    }
}
