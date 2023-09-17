package ru.skypro.homework.exceptions;

public class NotValidModelException extends Exception {
    public NotValidModelException(String txt) {
        super(txt);
    }

    public NotValidModelException() {
        super("Модель сформирована неверно");
    }
}
