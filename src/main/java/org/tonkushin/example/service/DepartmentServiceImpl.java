package org.tonkushin.example.service;

import org.springframework.stereotype.Service;
import org.tonkushin.example.exception.ItemNotFoundException;
import org.tonkushin.example.model.Department;
import org.tonkushin.example.repository.DepartmentRepository;

import java.util.List;

/**
 * Реализация сервиса для подразделений
 */

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentServiceImpl(DepartmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Department> findAll() {
        return repository.findAll();
    }

    @Override
    public Department findOne(Long id) {
        return repository.findById(id).orElseThrow(() -> new ItemNotFoundException(id, "Подразделение"));
    }

    @Override
    public Department save(Department item) {
        checkConstraints(item);

        return repository.save(item);
    }

    @Override
    public void delete(Department item) {
        repository.delete(item);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Long count() {
        return repository.count();
    }

    // Проверяет правильность заполнения объекта
    private void checkConstraints(Department item) {
        // Проверяем ограничения по полю Наименование
        if (item.getName() == null || item.getName().isEmpty()) {
            throw new RuntimeException("Поле 'Наименование' не должно быть пустым");
        } else if (item.getName() != null && item.getName().length() > 255) {
            throw new RuntimeException("Поле 'Наименование' не должно превышать 255 символов");
        }

        // Проверяем ограничения по полю Примечание
        if (item.getNotes() != null && item.getNotes().length() > 255) {
            throw new RuntimeException("Поле 'Примечание' не должно превышать 255 символов");
        }
    }
}
