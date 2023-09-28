package com.example.university.university.services;

import com.example.university.university.entities.Curriculum;
import com.example.university.university.repositories.CurriculumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurriculumService {

    private final CurriculumRepository curriculumRepository;

    @Autowired
    public CurriculumService(CurriculumRepository curriculumRepository) {
        this.curriculumRepository = curriculumRepository;
    }

    public List<Curriculum> getAllCurriculum() {
        return curriculumRepository.findAll();
    }

    // Другие методы, если необходимо
}
