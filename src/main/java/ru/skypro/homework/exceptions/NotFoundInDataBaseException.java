package ru.skypro.homework.exceptions;

import org.webjars.NotFoundException;

public class NotFoundInDataBaseException extends RuntimeException {

    public NotFoundInDataBaseException(String message) {
        super(message);
    }

    public NotFoundInDataBaseException() {
        super("Объект не найден в базе данных");
    }
}
