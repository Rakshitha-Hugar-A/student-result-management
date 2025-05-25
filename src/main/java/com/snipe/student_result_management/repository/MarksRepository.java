package com.snipe.student_result_management.repository;

import com.snipe.student_result_management.entity.Marks;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarksRepository extends JpaRepository <Marks, Long> {
	List<Marks> findAllByStudentId(Long studentId);
}
