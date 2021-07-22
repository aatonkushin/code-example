package org.tonkushin.example.repository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.tonkushin.example.model.Department;
import org.tonkushin.example.model.Profession;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для профессий
 */

@Repository
public interface ProfessionRepository extends CrudRepository<Profession, Long> {
    @Cacheable(value = "professions")
    Optional<Profession> findById(Long id);

    @CacheEvict(value = "professions")
    void deleteById(Long id);

    Optional<Profession> findByName(String name);

    List<Profession> findAll();
}
