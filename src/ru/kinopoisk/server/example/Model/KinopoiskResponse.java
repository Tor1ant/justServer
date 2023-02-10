package ru.kinopoisk.server.example.Model;

import java.util.ArrayList;
import java.util.List;

public class KinopoiskResponse {

    private final List<KinopoiskFilm> docs = new ArrayList<>();

    public List<KinopoiskFilm> KinopoiskFilms() {
        return docs;
    }
}