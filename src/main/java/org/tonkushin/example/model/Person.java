package org.tonkushin.example.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Модель для сотрудника
 */

@Entity
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id = -1L;                  // код в БД

    @Column(nullable = false)
    @NotBlank
    @Size(max = 255)
    private String name;                    // ФИО

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;          // отдел

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profession_id")
    private Profession profession;          // должность или профессия

    @Size(max = 255)
    private String notes;                   // примечание
}
