package org.tonkushin.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.tonkushin.example.model.Profession;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для профессий
 */

@Repository
public interface ProfessionRepository extends CrudRepository<Profession, Long> {
    Optional<Profession> findById(Long id);
    Optional<Profession> findByName(String name);

    List<Profession> findAll();
}
