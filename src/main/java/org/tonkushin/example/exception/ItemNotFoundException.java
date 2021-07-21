package org.tonkushin.example.exception;

/**
 * Исключение для случая, когда запись с указанным кодом не найдена
 **/
public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException() {
        super("Запись не найдена");
    }

    public ItemNotFoundException(Long id) {
        super(String.format("Запись с кодом %d не найдена", id));
    }

    public ItemNotFoundException(String name) {
        super(String.format("Запись с именем %s не найдена", name));
    }

    public ItemNotFoundException(Long id, String additionalInfo) {
        super(String.format("Запись с кодом %d не найдена (%s)", id, additionalInfo));
    }
}
