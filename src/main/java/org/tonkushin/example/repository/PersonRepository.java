package org.tonkushin.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.tonkushin.example.model.Person;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для сотрудников
 */

public interface PersonRepository extends CrudRepository<Person, Long> {
    Optional<Person> findById(Long id);

    List<Person> findAll();
}
