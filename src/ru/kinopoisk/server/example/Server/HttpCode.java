package ru.kinopoisk.server.example.Server;

public enum HttpCode {
    SUCESS(200),

    METHOD_NOT_ALLOWED(405);

    private int code;

    HttpCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
