package com.snipe.student_result_management.utils;


import java.util.*;
public class GradeUtils {
	public static String calculateGrade(int marks) {
        if (marks >= 90) return "A+";
        else if (marks >= 80) return "A";
        else if (marks >= 70) return "B+";
        else if (marks >= 60) return "B";
        else if (marks >= 50) return "C";
        else if (marks >= 35) return "D";  // NEW GRADE for just pass
        else return "F";
    }
	public static String calculateStatus(List<Integer> marksList) {
        // If any subject is fail (marks < 35), overall FAIL
        for (int mark : marksList) {
            if (mark < 35) return "FAIL";
        }
        return "PASS";
    }
	
	public static double calculatePercentage(List<Integer> marksList, int maxMarksPerSubject) {
	    if (marksList == null || marksList.isEmpty()) return 0.0;

	    int totalObtained = marksList.stream().mapToInt(Integer::intValue).sum();
	    int totalMax = marksList.size() * maxMarksPerSubject;

	    return (totalObtained * 100.0) / totalMax;
	}


}
