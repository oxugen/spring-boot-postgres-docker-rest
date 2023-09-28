package com.example.university.university.services;

import com.example.university.university.entities.Academic;
import com.example.university.university.repositories.AcademicRepository;
import com.example.university.university.serviceInterfaces.AcademicServiceI;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcademicService implements AcademicServiceI {

    @PersistenceContext
    private EntityManager entityManager;
    private final AcademicRepository academicRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AcademicService(AcademicRepository academicRepository) {
        this.academicRepository = academicRepository;
    }

    public List<Academic> getAllAcademicRecords() {
        return academicRepository.findAll();
    }

    public Academic getAcademicRecordById(Long id) {
        return academicRepository.findById(id).orElse(null);
    }

    public List<Academic> getAcademicRecordsByStudentId(Long studentId) {
        return academicRepository.findByStudentId(studentId);
    }

    @Override
    public List<Academic> getAcademicRecordsByCurriculumId(Long curriculumId) {
        return academicRepository.findByCurriculumId(curriculumId);
    }

    public void addAcademicRecord(Academic academicRecord) {
        academicRepository.save(academicRecord);
    }

    @Override
    public void updateAcademicRecord(Long id, Academic academicRecord) {
        Academic existingRecord = academicRepository.findById(id).orElse(null);
        if (existingRecord != null) {
            existingRecord.setYear(academicRecord.getYear());
            existingRecord.setSemester(academicRecord.getSemester());
            existingRecord.setStudent(academicRecord.getStudent());
            existingRecord.setCurriculum(academicRecord.getCurriculum());
            existingRecord.setGrade(academicRecord.getGrade());
            academicRepository.save(existingRecord);
        }
    }

    @Override
    public void deleteAcademicRecord(Long id) {
        academicRepository.deleteById(id);
    }

    public Double calculateStudentAvgGrade(Long studentId, String subject) {
        String sql = "SELECT * FROM calculate_student_avg_grade(?, ?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{studentId, subject}, Double.class);
    }


}
