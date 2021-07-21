package org.tonkushin.example.service;

import org.springframework.stereotype.Service;
import org.tonkushin.example.exception.ItemNotFoundException;
import org.tonkushin.example.model.Person;
import org.tonkushin.example.repository.PersonRepository;

import java.util.List;

/**
 * Реализация сервиса для сотрудников
 */

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;

    public PersonServiceImpl(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Person> findAll() {
        return repository.findAll();
    }

    @Override
    public Person findOne(Long id) {
        return repository.findById(id).orElseThrow(() -> new ItemNotFoundException(id, "Персонал"));
    }

    @Override
    public Person save(Person item) {
        checkConstraints(item);

        return repository.save(item);
    }

    private void checkConstraints(Person item) {
        // Проверяем ограничения по полю Наименование
        if (item.getName() == null || item.getName().isEmpty()) {
            throw new RuntimeException("Поле 'Наименование' не должно быть пустым");
        } else if (item.getName() != null && item.getName().length() > 255) {
            throw new RuntimeException("Поле 'Наименование' не должно превышать 255 символов");
        }

        // Проверяем ограничения по полю Подразделение
        if (item.getDepartment() == null) {
            throw new RuntimeException("Поле 'Подразделение' не должно быть пустым");
        }

        // Проверяем ограничения по полю Профессия
        if (item.getProfession() == null) {
            throw new RuntimeException("Поле 'Профессия' не должно быть пустым");
        }

        // Проверяем ограничения по полю Примечание
        if (item.getNotes() != null && item.getNotes().length() > 255) {
            throw new RuntimeException("Поле 'Примечание' не должно превышать 255 символов");
        }
    }

    @Override
    public void delete(Person item) {
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
}
