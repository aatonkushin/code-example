package org.tonkushin.example.exception;

/**
 * Исключение для случая, когда невозможно удалить запись
 */

public class CanNotDeleteException extends RuntimeException {
    public CanNotDeleteException() {
        super("Невозможно удалить запись, т.к. есть записи, ссылающиеся на нее");
    }
}
