package com.snipe.student_result_management.service.impl;

import com.snipe.student_result_management.entity.Marks;
import com.snipe.student_result_management.entity.Student;
import com.snipe.student_result_management.repository.StudentRepository;
import com.snipe.student_result_management.service.StudentService;
import com.snipe.student_result_management.utils.GradeUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
    
    @Override
    public Student updateStudent(Long id, Student updatedStudent) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(optionalStudent.isPresent()) {
            Student existingStudent = optionalStudent.get();
            existingStudent.setName(updatedStudent.getName());
            existingStudent.setCourse(updatedStudent.getCourse());
            existingStudent.setEmail(updatedStudent.getEmail());
            existingStudent.setRollNo(updatedStudent.getRollNo());
            // Add any other fields you want to update
            return studentRepository.save(existingStudent);
        } else {
            throw new RuntimeException("Student not found with id " + id);
        }
    }

    
    
    @Override
    public String getResultStatusForStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                        .orElseThrow(() -> new RuntimeException("Student not found"));
        List<Integer> marksList = student.getMarks().stream()
                                .map(Marks::getMarksObtained)
                                .collect(Collectors.toList());
        return GradeUtils.calculateStatus(marksList);
    }

}
