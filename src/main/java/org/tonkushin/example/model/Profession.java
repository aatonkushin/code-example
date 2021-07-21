package org.tonkushin.example.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Модель для профессии
 */

@Entity
@Data
public class Profession {
    @Id
    private long id = -1L;      // код в БД
    @Column(nullable = false, unique = true)
    private String name;        // наименование
    private String notes;       // примечание
}
