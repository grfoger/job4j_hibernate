package ru.job4j.hql.model;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "vacancies")
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private String title;
    private String description;

    public static Vacancy of(String title, String description){
        Vacancy v = new Vacancy();
        v.title = title;
        v.description = description;
        return v;
    }
}
