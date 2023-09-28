package com.example.university.university.repositories;

import com.example.university.university.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Procedure("add_student_proc")
    void addStudent(
            @Param("last_name") String last_name,
            @Param("first_name") String first_name,
            @Param("middle_name") String middle_name,
            @Param("admission_year") Integer admission_year,
            @Param("study_form") String study_form,
            @Param("group_number_arg") String group_number);
}
