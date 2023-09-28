package com.example.university.university.controllers;

import com.example.university.university.entities.Academic;
import com.example.university.university.services.AcademicService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/academic")
public class AcademicController {

    private final AcademicService academicService;
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    public AcademicController(AcademicService academicService) {
        this.academicService = academicService;
    }

    @GetMapping("/all")
    public List<Academic> getAllAcademicRecords() {
        return academicService.getAllAcademicRecords();
    }


    @GetMapping("/by-curriculum/{curriculumId}")
    public ResponseEntity<List<Academic>> getAcademicRecordsByCurriculumId(@PathVariable Long curriculumId) {
        List<Academic> academicRecords = academicService.getAcademicRecordsByCurriculumId(curriculumId);
        return new ResponseEntity<>(academicRecords, HttpStatus.OK);
    }

    @GetMapping("/by-student/{studentId}")
    public ResponseEntity<List<Academic>> getAcademicRecordsByStudentId(@PathVariable Long studentId) {
        List<Academic> academicRecords = academicService.getAcademicRecordsByStudentId(studentId);
        return new ResponseEntity<>(academicRecords, HttpStatus.OK);
    }

    @GetMapping("/{recordId}")
    public ResponseEntity<Academic> getAcademicById(@PathVariable Long recordId) {
        Academic academicRecord = academicService.getAcademicRecordById(recordId);
        if (academicRecord != null) {
            return new ResponseEntity<>(academicRecord, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Void> addAcademicRecord(@RequestBody Academic academicRecord) {
        academicService.addAcademicRecord(academicRecord);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{recordId}")
    public ResponseEntity<Void> updateAcademicRecord(@PathVariable Long recordId, @RequestBody Academic academicRecord) {
        academicService.updateAcademicRecord(recordId, academicRecord);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{recordId}")
    public ResponseEntity<Void> deleteAcademicRecord(@PathVariable Long recordId) {
        academicService.deleteAcademicRecord(recordId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
