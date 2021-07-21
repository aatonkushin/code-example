package org.tonkushin.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.tonkushin.example.model.Department;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для подразделений
 */

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {
    Optional<Department> findById(Long id);

    List<Department> findAll();
}
