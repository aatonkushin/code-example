package org.tonkushin.example.repository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.tonkushin.example.model.Person;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для сотрудников
 */

public interface PersonRepository extends CrudRepository<Person, Long> {
    @Cacheable(value = "personal")
    Optional<Person> findById(Long id);

    @CacheEvict(value = "personal")
    void deleteById(Long id);

    List<Person> findAll();

    @Cacheable(value = "personal_departments_count")
    Long countAllByDepartment_Id(long departmentId);

    @Cacheable(value = "personal_professions_count")
    Long countAllByProfession_Id(long professionId);
}
