package com.snipe.student_result_management.service;

import com.snipe.student_result_management.entity.Student;
import java.util.Optional;
import java.util.*;

public interface StudentService {
	Optional<Student> getStudentById(Long id);
    Student saveStudent(Student student);
    List<Student> getAllStudents();
    Student updateStudent(Long id, Student student);
    void deleteStudent(Long id);
    String getResultStatusForStudent(Long studentId);


}
