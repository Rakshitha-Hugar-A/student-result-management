package com.snipe.student_result_management.service.impl;

import com.snipe.student_result_management.entity.Marks;
import com.snipe.student_result_management.entity.Student;
import com.snipe.student_result_management.repository.MarksRepository;
import com.snipe.student_result_management.repository.StudentRepository;
import com.snipe.student_result_management.service.MarksService;
import com.snipe.student_result_management.utils.GradeUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarksServiceImpl implements MarksService {

    private final MarksRepository marksRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public MarksServiceImpl(MarksRepository marksRepository ,StudentRepository studentRepository ) {
        this.marksRepository = marksRepository;
		this.studentRepository = studentRepository;
    }

    @Override
    public Marks saveMarks(Marks marks) {
    	Long studentId = marks.getStudent().getId();

        // Step 2: Fetch full student entity from DB
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));

        // Step 3: Assign full student to marks
        marks.setStudent(student);

        // Step 4: Calculate grade
    	  String grade = GradeUtils.calculateGrade(marks.getMarksObtained());
    	    marks.setGrade(grade);
    	    return marksRepository.save(marks);
    }

    @Override
    public Optional<Marks> getMarksById(Long id) {
        return marksRepository.findById(id);
    }

    @Override
    public List<Marks> getAllMarks() {
        return marksRepository.findAll();
    }

    @Override
    public void deleteMarks(Long id) {
        marksRepository.deleteById(id);
    }
    @Override
    public List<Marks> findAllByStudentId(Long studentId) {
        return marksRepository.findAllByStudentId(studentId);
    }
    @Override
    public void deleteMarksById(Long id) {
        marksRepository.deleteById(id);
    }

    

}
