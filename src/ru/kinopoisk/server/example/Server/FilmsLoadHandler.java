package ru.kinopoisk.server.example.Server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ru.kinopoisk.server.example.Managers.FilmsManager;

import java.io.IOException;
import java.io.OutputStream;

import java.nio.charset.StandardCharsets;

import static ru.kinopoisk.server.example.Server.HttpCode.METHOD_NOT_ALLOWED;
import static ru.kinopoisk.server.example.Server.HttpCode.SUCESS;
import static ru.kinopoisk.server.example.Server.HttpMethod.GET;

public class FilmsLoadHandler implements HttpHandler {
    private final FilmsManager filmsManager;

    public FilmsLoadHandler(FilmsManager filmsManager) {
        this.filmsManager = filmsManager;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();
        if (!requestMethod.equals(GET.name())) {
            exchange.sendResponseHeaders(METHOD_NOT_ALLOWED.getCode(), 0);
            try (OutputStream outputStream = exchange.getResponseBody()) {
                {
                    outputStream.write("Необходимо передать метод GET".getBytes(StandardCharsets.UTF_8));
                }
                exchange.close();
                return;
            }
        }
        filmsManager.loadFilms();
        writeResponse(exchange, SUCESS.getCode(), "Фильмы успешно загружены");
    }

    private void writeResponse(HttpExchange exchange, int code, String response) throws IOException {
        exchange.sendResponseHeaders(code, 0);
        try (OutputStream outputStream = exchange.getResponseBody()) {
            {
                outputStream.write("Фильмы успешно загружены".getBytes(StandardCharsets.UTF_8));
            }
            exchange.close();
        }
    }
}
