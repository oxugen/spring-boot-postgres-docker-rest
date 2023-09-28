package com.example.university.university.repositories;

import com.example.university.university.entities.Curriculum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {
    // Можно добавить дополнительные методы для работы с данными таблицы curriculum
}
