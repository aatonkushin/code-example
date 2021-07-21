package org.tonkushin.example.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Модель для сотрудника
 */

@Entity
@Data
public class Person {
    @Id
    private long id = -1L;                  // код в БД
    @Column(nullable = false)
    private String name;                    // ФИО

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;          // отдел

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profession_id")
    private Profession profession;          // должность или профессия

    private String notes;                   // примечание
}
