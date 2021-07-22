package org.tonkushin.example.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Модель для отдела или подразделения
 */

@Entity
@Data
public class Department {
    @Id
    private long id = -1L;      // код в БД
    @Column(nullable = false)
    @NotBlank
    @Size(max = 255)
    private String name;        // наименование
    @Size(max = 255)
    private String notes;       // примечание
}
