package com.example.university.university.serviceInterfaces;

import com.example.university.university.entities.Academic;

import java.util.List;

public interface AcademicServiceI {
    List<Academic> getAllAcademicRecords();
    Academic getAcademicRecordById(Long id);
    List<Academic> getAcademicRecordsByStudentId(Long studentId);
    List<Academic> getAcademicRecordsByCurriculumId(Long curriculumId);
    void addAcademicRecord(Academic academicRecord);
    void updateAcademicRecord(Long id, Academic academicRecord);
    void deleteAcademicRecord(Long id);
}
