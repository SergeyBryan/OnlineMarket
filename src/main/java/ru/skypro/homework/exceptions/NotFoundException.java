package ru.skypro.homework.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String str) {
        super(str);
    }


    public NotFoundException() {
        super("Ошибка при создании сущности");
    }
}
