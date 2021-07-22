package org.tonkushin.example.repository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.tonkushin.example.model.Department;
import org.tonkushin.example.model.Person;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для подразделений
 */

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {
    @Cacheable(value = "departments")
    Optional<Department> findById(Long id);

    @CacheEvict(value = "departments")
    void deleteById(Long id);

    List<Department> findAll();
}
