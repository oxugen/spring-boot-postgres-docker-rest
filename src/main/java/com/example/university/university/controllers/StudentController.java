package com.example.university.university.controllers;

import com.example.university.university.entities.Academic;
import com.example.university.university.entities.Curriculum;
import com.example.university.university.entities.Student;
import com.example.university.university.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/all")
    public List<Student> getAllCurriculum() {
        return studentService.getAllStudents();
    }

    @PostMapping("/add")
    public ResponseEntity<String> addStudent(
            @RequestParam("lastName") String lastName,
            @RequestParam("firstName") String firstName,
            @RequestParam("middleName") String middleName,
            @RequestParam("admissionYear") Integer admissionYear,
            @RequestParam("studyForm") String studyForm,
            @RequestParam("groupNumber") String groupNumber) {
        try {
            studentService.addStudent(lastName, firstName, middleName, admissionYear, studyForm, groupNumber);
            return ResponseEntity.ok("Student added successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{recordId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long recordId) {
        Student studentRecord = studentService.getStudentRecordById(recordId);
        if (studentRecord != null) {
            return new ResponseEntity<>(studentRecord, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Void> addStudentRecord(@RequestBody Student studentRecord) {
        studentService.addStudentRecord(studentRecord);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{recordId}")
    public ResponseEntity<Void> updateStudentRecord(@PathVariable Long recordId, @RequestBody Student studentRecord) {
        studentService.updateStudentRecord(recordId, studentRecord);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{recordId}")
    public ResponseEntity<Void> deleteStudentRecord(@PathVariable Long recordId) {
        studentService.deleteStudentRecord(recordId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
