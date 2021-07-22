package org.tonkushin.example.exception;

/**
 * Используется при ошибках проверки полей
 */

public class CheckConstraintsException extends RuntimeException{
    public CheckConstraintsException() {
        super("Ошибка проверки полей");
    }

    public CheckConstraintsException(String message) {
        super(message);
    }
}
