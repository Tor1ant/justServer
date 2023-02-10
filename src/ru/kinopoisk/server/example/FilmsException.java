package ru.kinopoisk.server.example;

public class FilmsException extends RuntimeException {
    public FilmsException(String message) {
        super(message);
    }

    public FilmsException(String message, Throwable cause) {
        super(message, cause);
    }

}
