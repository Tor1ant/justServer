package ru.kinopoisk.server.example;

import com.google.gson.Gson;
import ru.kinopoisk.server.example.Model.KinopoiskResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class KinopoiskClient {
    Gson gson = new Gson();
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private String token;

    public KinopoiskClient(String token) {
        this.token = token;
    }

    public KinopoiskResponse getFilms() {
        URI uri = buildURI(8, 10, 2020, 2021);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .header("Content-type", "application/json")
                .uri(uri)
                .build();
        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return gson.fromJson(httpResponse.body(), KinopoiskResponse.class);
        } catch (IOException | InterruptedException e) {
            throw new FilmsException("не могу получить данные от кинопоиска", e);
        }
    }

    private URI buildURI(int ratingFrom, int ratingTo, int yearFrom, int yearTo) {
        return URI.create("https://api.kinopoisk.dev/movie?" +
                "field=rating.kp&search=" + ratingFrom + "-" + ratingTo +
                "&field=year" +
                "&search=" + yearFrom + "-" + yearTo +
                "&field=typeNumber" +
                "&search=1" +
                "&token=" + token +
                "&limit=100");
    }
}