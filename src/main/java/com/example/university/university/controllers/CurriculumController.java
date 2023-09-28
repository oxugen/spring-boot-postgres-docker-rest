package com.example.university.university.controllers;

import com.example.university.university.entities.Curriculum;
import com.example.university.university.services.CurriculumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/curriculum")
public class CurriculumController {

    private final CurriculumService curriculumService;

    @Autowired
    public CurriculumController(CurriculumService curriculumService) {
        this.curriculumService = curriculumService;
    }

    @GetMapping("/all")
    public List<Curriculum> getAllCurriculum() {
        return curriculumService.getAllCurriculum();
    }

    // Другие методы контроллера для работы с curriculum
}

