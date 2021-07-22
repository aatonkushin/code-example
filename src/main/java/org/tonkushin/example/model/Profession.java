package org.tonkushin.example.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Модель для профессии
 */

@Entity
@Data
public class Profession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id = -1L;      // код в БД

    @Column(nullable = false, unique = true)
    @NotBlank
    @Size(max = 255)
    private String name;        // наименование

    @Size(max = 255)
    private String notes;       // примечание
}
