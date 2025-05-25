package com.snipe.student_result_management.controller;

import com.snipe.student_result_management.entity.Marks;
import com.snipe.student_result_management.entity.Student;
import com.snipe.student_result_management.service.MarksService;
import com.snipe.student_result_management.service.StudentService;
import com.snipe.student_result_management.utils.GradeUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/marks")
public class MarksViewController {

    @Autowired
    private MarksService marksService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/add")
    public String showAddMarksForm(@RequestParam("studentId") Long studentId, Model model) {
        Student student = studentService.getStudentById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));

        Marks marks = new Marks();
        marks.setStudent(student); // Pre-fill student in marks object

        model.addAttribute("marks", marks);
              return "marks/add";
    }


//    @PostMapping("/add")
//    public String saveMarks(@ModelAttribute("marks") Marks marks) {
//        marksService.saveMarks(marks);
//        return "redirect:/marks/add?success";
//    }
    @PostMapping("/add")
    public String saveMarks(@ModelAttribute("marks") Marks marks) {
        Long studentId = marks.getStudent().getId(); // Get student ID from form-bound object
        marksService.saveMarks(marks);
        return "redirect:/marks/add?studentId=" + studentId + "&success";
    }
    
    
    @GetMapping("/edit/{id}")
    public String showEditMarksForm(@PathVariable("id") Long marksId, Model model) {
        Marks marks = marksService.getMarksById(marksId)
                .orElseThrow(() -> new RuntimeException("Marks not found with ID: " + marksId));
        model.addAttribute("marks", marks);
        return "marks/add";  // Reusing the same form
    }
//
//    @PostMapping("/update")
//    public String updateMarks(@ModelAttribute("marks") Marks marks) {
//        marksService.saveMarks(marks);  // Save or update based on ID
//        return "redirect:/marks/view_all?updated";
//    }
    @PostMapping("/update")
    public String updateMarks(@ModelAttribute("marks") Marks marks) {
        marksService.saveMarks(marks);
        return "redirect:/marks/edit/" + marks.getId() + "?updated";
    }

    
    @GetMapping("/view_all")
    public String showAllMarksPage(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "marks/view_all"; // your Thymeleaf template for the dashboard
    }

    @GetMapping("/edit-by-student/{studentId}")
    public String showMarksListByStudent(@PathVariable Long studentId, Model model) {
        List<Marks> marksList = marksService.findAllByStudentId(studentId);
        Student student = studentService.getStudentById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        model.addAttribute("marksList", marksList);
        model.addAttribute("student", student);
        return "marks/edit-list";  // create this Thymeleaf page next
    }

    
    @GetMapping("/delete/{id}")
    public String deleteMarks(@PathVariable("id") Long id) {
        marksService.deleteMarksById(id);
        return "redirect:/marks/view_all?deleted";  // or redirect back to the marks list page for that student
    }

    @ModelAttribute("subjects")
    public List<String> subjects() {
        return List.of("Math", "Science", "English"); // example
    }
    
    
    @GetMapping("/report/{studentId}")
    public String showReportCard(@PathVariable Long studentId, Model model) {
        Student student = studentService.getStudentById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));

        List<Marks> marksList = marksService.findAllByStudentId(studentId);

        // Extract marks only
        List<Integer> marksOnly = marksList.stream()
                .map(Marks::getMarksObtained)
                .toList();

        // Use GradeUtils for percentage and pass/fail status
        double percentage = GradeUtils.calculatePercentage(marksOnly, 100); // assuming 100 marks per subject
        String status = GradeUtils.calculateStatus(marksOnly); // "PASS" or "FAIL"

        boolean passed = status.equals("PASS");
        String statusMessage = passed ? "🎉 Congratulations! You passed." : "❌ Better luck next time!";

        model.addAttribute("student", student);
        model.addAttribute("marksList", marksList);
        model.addAttribute("percentage", percentage);
        model.addAttribute("passed", passed);
        model.addAttribute("statusMessage", statusMessage);

        return "marks/report-card";
    }



}