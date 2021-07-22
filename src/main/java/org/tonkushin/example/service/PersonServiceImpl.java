package org.tonkushin.example.service;

import org.springframework.stereotype.Service;
import org.tonkushin.example.exception.CheckConstraintsException;
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

    @Override
    public Person update(Person item, Long id) {
        checkConstraints(item);

        return repository.findById(id).map(found -> {
            found.setName(item.getName());
            found.setNotes(item.getNotes());
            found.setDepartment(item.getDepartment());
            found.setProfession(item.getProfession());
            return repository.save(found);
        }).orElseThrow(ItemNotFoundException::new);
    }

    private void checkConstraints(Person item) {
        // Проверяем ограничения по полю Наименование
        if (item.getName() == null || item.getName().isEmpty()) {
            throw new CheckConstraintsException("Поле 'Наименование' не должно быть пустым");
        } else if (item.getName() != null && item.getName().length() > 255) {
            throw new CheckConstraintsException("Поле 'Наименование' не должно превышать 255 символов");
        }

        // Проверяем ограничения по полю Подразделение
        if (item.getDepartment() == null) {
            throw new CheckConstraintsException("Поле 'Подразделение' не должно быть пустым");
        }

        // Проверяем ограничения по полю Профессия
        if (item.getProfession() == null) {
            throw new CheckConstraintsException("Поле 'Профессия' не должно быть пустым");
        }

        // Проверяем ограничения по полю Примечание
        if (item.getNotes() != null && item.getNotes().length() > 255) {
            throw new CheckConstraintsException("Поле 'Примечание' не должно превышать 255 символов");
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
