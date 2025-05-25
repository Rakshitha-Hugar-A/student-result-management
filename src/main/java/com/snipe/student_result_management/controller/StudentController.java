package com.snipe.student_result_management.controller;

import com.snipe.student_result_management.entity.Student;
import com.snipe.student_result_management.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")

public class StudentController {
	 private final StudentService studentService;
	 
	    @Autowired
	    public StudentController(StudentService studentService) {
	        this.studentService = studentService;
	    }

	    // Create student
	    @PostMapping
	    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
	        Student savedStudent = studentService.saveStudent(student);
	        return ResponseEntity.ok(savedStudent);
	    }

	    // Get all students
	    @GetMapping
	    public ResponseEntity<List<Student>> getAllStudents() {
	        List<Student> students = studentService.getAllStudents();
	        return ResponseEntity.ok(students);
	    }

	    // Get student by ID
	    @GetMapping("/{id}")
	    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
	        Optional<Student> student = studentService.getStudentById(id);
	        return student.map(ResponseEntity::ok)
	                      .orElse(ResponseEntity.notFound().build());
	    }

	    // Delete student by ID
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
	        studentService.deleteStudent(id);
	        return ResponseEntity.noContent().build();
	    }

}
