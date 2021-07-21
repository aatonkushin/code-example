package org.tonkushin.example.service;

import java.util.List;

/**
 * Общий интерфейс для всех CRUD-сервисов приложения
 * @param <T> - тип сущности с которой работает сервис
 */

public interface CrudService<T> {
    /**
     * Поиск всех записей в БД
     * @return все записи
     */
    List<T> findAll();

    /**
     * Поиск записи по коду
     * @param id код
     * @return запись
     */
    T findOne(Long id);

    /**
     * Сохраняет запись в БД
     * @param item элемент для сохранения
     * @return сохранённую запись
     */
    T save(T item);

    /**
     * Удаляет элемент из БД
     * @param item элемент для удаления
     */
    void delete(T item);

    /**
     * Удаляет элемент с кодом id из БД
     * @param id код элемента
     */
    void delete(Long id);

    /**
     * Возвращает количество элементов из БД
     * @return количество элементов из БД
     */
    Long count();
}
