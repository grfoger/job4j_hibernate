package ru.job4j.hql.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "candidates")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private String name;
    private int experience;
    private double salary;

    @OneToOne(fetch = FetchType.LAZY)
    private VacancyBase vacancyBase;

    public static Candidate of(String name, int experience, double salary) {
        Candidate candidate = new Candidate();
        candidate.name = name;
        candidate.experience = experience;
        candidate.salary = salary;
        return candidate;
    }

}