package org.tonkushin.example.service;

import org.springframework.stereotype.Service;
import org.tonkushin.example.exception.CanNotDeleteException;
import org.tonkushin.example.exception.CheckConstraintsException;
import org.tonkushin.example.exception.ItemNotFoundException;
import org.tonkushin.example.model.Department;
import org.tonkushin.example.repository.DepartmentRepository;
import org.tonkushin.example.repository.PersonRepository;

import java.util.List;

/**
 * Реализация сервиса для подразделений
 */

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository repository;
    private final PersonRepository personRepository;

    public DepartmentServiceImpl(DepartmentRepository repository, PersonRepository personRepository) {
        this.repository = repository;
        this.personRepository = personRepository;
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
    public Department update(Department item, Long id) {
        checkConstraints(item);

        return repository.findById(id).map(found -> {
            found.setName(item.getName());
            found.setNotes(item.getNotes());
            return repository.save(found);
        }).orElseThrow(ItemNotFoundException::new);
    }

    @Override
    public void delete(Department item) {
        delete(item.getId());
    }

    @Override
    public void delete(Long id) {
        // Проверяем, что нет сотрудников, относящихся к указанному отделу
        if (personRepository.countAllByDepartment_Id(id) > 0) {
            throw new CanNotDeleteException();
        }

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
            throw new CheckConstraintsException("Поле 'Наименование' не должно быть пустым");
        } else if (item.getName() != null && item.getName().length() > 255) {
            throw new CheckConstraintsException("Поле 'Наименование' не должно превышать 255 символов");
        }

        // Проверяем ограничения по полю Примечание
        if (item.getNotes() != null && item.getNotes().length() > 255) {
            throw new CheckConstraintsException("Поле 'Примечание' не должно превышать 255 символов");
        }
    }
}
