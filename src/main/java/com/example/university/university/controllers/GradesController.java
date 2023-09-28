package com.example.university.university.controllers;

import com.example.university.university.services.AcademicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grades")
public class GradesController {

    private final AcademicService academicRecordService;

    @Autowired
    public GradesController(AcademicService academicRecordService) {
        this.academicRecordService = academicRecordService;
    }

    @GetMapping("/average")
    public ResponseEntity<Double> getAverageGrade(
            @RequestParam Long studentId,
            @RequestParam String subject) {
        Double averageGrade = academicRecordService.calculateStudentAvgGrade(studentId, subject);
        if (averageGrade != null) {
            return new ResponseEntity<>(averageGrade, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
