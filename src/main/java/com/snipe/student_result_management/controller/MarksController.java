package com.snipe.student_result_management.controller;
import com.snipe.student_result_management.entity.Marks;
import com.snipe.student_result_management.service.MarksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/marks")
public class MarksController {

	 private final MarksService marksService;

	    @Autowired
	    public MarksController(MarksService marksService) {
	        this.marksService = marksService;
	    }

	    @PostMapping
	    public ResponseEntity<Marks> createMarks(@RequestBody Marks marks) {
	        Marks savedMarks = marksService.saveMarks(marks);
	        return ResponseEntity.ok(savedMarks);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Marks> getMarksById(@PathVariable Long id) {
	        Optional<Marks> marks = marksService.getMarksById(id);
	        return marks.map(ResponseEntity::ok)
	                .orElseGet(() -> ResponseEntity.notFound().build());
	    }

	    @GetMapping
	    public ResponseEntity<List<Marks>> getAllMarks() {
	        List<Marks> allMarks = marksService.getAllMarks();
	        return ResponseEntity.ok(allMarks);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteMarks(@PathVariable Long id) {
	        marksService.deleteMarks(id);
	        return ResponseEntity.noContent().build();
	    }
}
