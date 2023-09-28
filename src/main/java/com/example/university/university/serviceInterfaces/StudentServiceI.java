package com.example.university.university.serviceInterfaces;

import com.example.university.university.entities.Academic;
import com.example.university.university.entities.Student;

public interface StudentServiceI {

    Student getStudentRecordById(Long id);
    void addStudentRecord(Student studentRecord);
    void updateStudentRecord(Long id, Student studentRecord);
    void deleteStudentRecord(Long id);
}
