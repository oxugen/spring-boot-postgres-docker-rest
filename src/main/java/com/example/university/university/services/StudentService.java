package com.example.university.university.services;

import com.example.university.university.entities.Academic;
import com.example.university.university.entities.Curriculum;
import com.example.university.university.entities.Student;
import com.example.university.university.repositories.StudentRepository;
import com.example.university.university.serviceInterfaces.StudentServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements StudentServiceI {
    @Autowired
    private StudentRepository studentRepository;
    public void addStudent(String lastName, String firstName, String middleName, Integer admissionYear, String studyForm, String groupNumber) {
        studentRepository.addStudent(lastName, firstName, middleName, admissionYear, studyForm, groupNumber);
    }

    @Override
    public Student getStudentRecordById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public void addStudentRecord(Student studentRecord) {
        studentRepository.save(studentRecord);
    }

    @Override
    public void updateStudentRecord(Long id, Student studentRecord) {
        Student existingRecord = studentRepository.findById(id).orElse(null);
        if (existingRecord != null) {
            existingRecord.setAdmissionYear(studentRecord.getAdmissionYear());
            existingRecord.setFirstName(studentRecord.getFirstName());
            existingRecord.setLastName(studentRecord.getLastName());
            existingRecord.setMiddleName(studentRecord.getMiddleName());
            existingRecord.setGroupNumber(studentRecord.getGroupNumber());
            existingRecord.setStudyForm(studentRecord.getStudyForm());
            studentRepository.save(existingRecord);
        }
    }

    @Override
    public void deleteStudentRecord(Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
