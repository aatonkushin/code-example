package org.tonkushin.example.service;

import org.springframework.stereotype.Service;
import org.tonkushin.example.exception.ItemNotFoundException;
import org.tonkushin.example.model.Profession;
import org.tonkushin.example.repository.ProfessionRepository;

import java.util.List;
import java.util.Optional;

/**
 * Реализация сервиса для профессий
 */

@Service
public class ProfessionServiceImpl implements ProfessionService {

    private final ProfessionRepository repository;

    public ProfessionServiceImpl(ProfessionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Profession> findAll() {
        return repository.findAll();
    }

    @Override
    public Profession findOne(Long id) {
        return repository.findById(id).orElseThrow(() -> new ItemNotFoundException(id, "Профессия"));
    }

    @Override
    public Profession save(Profession item) {
        checkConstraints(item);

        return repository.save(item);
    }

    @Override
    public void delete(Profession item) {
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
    private void checkConstraints(Profession item) {
        // Проверяем ограничения по полю Наименование
        if (item.getName() == null || item.getName().isEmpty()) {
            throw new RuntimeException("Поле 'Наименование' не должно быть пустым");
        } else if (item.getName() != null && item.getName().length() > 255) {
            throw new RuntimeException("Поле 'Наименование' не должно превышать 255 символов");
        }

        // Проверяем, что профессии с таким именем не существует
        Optional<Profession> opt = repository.findByName(item.getName());

        if (opt.isPresent() && opt.get().getId() != item.getId()) {
            throw new RuntimeException(String.format("Профессия с именем '%s' уже существует", item.getName()));
        }

        // Проверяем ограничения по полю Примечание
        if (item.getNotes() != null && item.getNotes().length() > 255) {
            throw new RuntimeException("Поле 'Примечание' не должно превышать 255 символов");
        }
    }
}
