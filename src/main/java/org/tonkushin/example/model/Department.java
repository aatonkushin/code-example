package org.tonkushin.example.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Модель для отдела или подразделения
 */

@Entity
@Data
public class Department {
    @Id
    private long id = -1L;      // код в БД
    @Column(nullable = false)
    private String name;        // наименование
    private String notes;       // примечание
}
