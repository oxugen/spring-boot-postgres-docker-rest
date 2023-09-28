package com.example.university.university.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "curriculum")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Curriculum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "curriculum_id")
    private Long curriculumId;

    @Column(name = "discipline")
    private String discipline;

    @Column(name = "semester")
    private Integer semester;

    @Column(name = "hours")
    private Integer hours;

    @Column(name = "exam_or_credit")
    private String examOrCredit;
}
