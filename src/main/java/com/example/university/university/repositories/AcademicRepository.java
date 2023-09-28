package com.example.university.university.repositories;

import com.example.university.university.entities.Academic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcademicRepository extends JpaRepository<Academic, Long> {
    @Query("SELECT ar FROM Academic ar WHERE ar.curriculum.curriculumId = :curriculumId")
    List<Academic> findByCurriculumId(@Param("curriculumId") Long curriculumId);

    @Query("SELECT ar FROM Academic ar WHERE ar.student.student_id = :student_id")
    List<Academic> findByStudentId(@Param("student_id") Long student_id);

    @Procedure(name = "calculate_student_avg_grade")
    Double calculateStudentAvgGrade(Long studentId, String subject);
}
