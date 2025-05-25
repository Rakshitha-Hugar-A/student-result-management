package com.snipe.student_result_management.service;

import com.snipe.student_result_management.entity.Marks;
import java.util.List;
import java.util.Optional;


public interface MarksService {
	Marks saveMarks(Marks marks);
    Optional<Marks> getMarksById(Long id);
    List<Marks> getAllMarks();
    void deleteMarks(Long id);
    List<Marks> findAllByStudentId(Long studentId);
    void deleteMarksById(Long id);


}
