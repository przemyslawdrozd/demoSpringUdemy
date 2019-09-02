package com.przemo.demo.student;

import com.przemo.demo.exceptions.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Student> getAllStudents() {
//        throw new ApiRequestException("Opps cannot get all students with Custom Exception");
        return studentService.getAllStudents();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addNewStudent(@RequestBody @Valid Student newStudent) {
        studentService.addNewStudent(newStudent);
    }

    @RequestMapping(method = RequestMethod.GET ,
    path = "/course/{student_id}")
    public List<Course> getStudentCourseById(@PathVariable("student_id") String student_id) {
        return studentService.getStudentCourseById(student_id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{student_id}")
    public Student getStudentById(@PathVariable("student_id") String studentId) {
        return studentService.getStudentById(studentId);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/course/info/{student_id}")
    public List<StudentCourse> getStudentCourseInfoById(@PathVariable("student_id") String studentId) {
        return studentService.getStudentCourseInfoById(studentId);
    }
}
