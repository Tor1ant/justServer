package ru.kinopoisk.server.example.Managers;

import ru.kinopoisk.server.example.FilmsException;
import ru.kinopoisk.server.example.KinopoiskClient;
import ru.kinopoisk.server.example.Model.KinopoiskFilm;
import ru.kinopoisk.server.example.Model.KinopoiskResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilmsManager {
    private final KinopoiskClient kinopoiskClient;
    private final List<KinopoiskFilm> kinopoiskFilms = new ArrayList<>();
    private final Map<String, Function<Object, Predicate<KinopoiskFilm>>> map = new HashMap<>();

    public FilmsManager(KinopoiskClient kinopoiskClient) {
        map.put("byRating", rating -> kinopoiskFilm -> kinopoiskFilm.getRating().getKp() > (double) rating);
        map.put("byLength", length -> kinopoiskFilm -> kinopoiskFilm.getMovieLength() > (int) length);
        this.kinopoiskClient = kinopoiskClient;
    }
    public List<KinopoiskFilm> getByFilter(Map<String, Object> request) {
        var predicate = request.entrySet().stream()
                .map(entry -> map.get(entry.getKey()).apply(entry.getValue()))
                .collect(Collectors.toList());
        var soloPredicate = predicate.stream().reduce(Predicate::and).get();
        return kinopoiskFilms.stream().filter(soloPredicate).collect(Collectors.toList());
    }

    public void loadFilms() {
        KinopoiskResponse kinopoiskResponse = kinopoiskClient.getFilms();
        kinopoiskFilms.addAll(kinopoiskResponse.KinopoiskFilms());
    }

    public List<String> getFilmNames() {
        return kinopoiskFilms.stream()
                .map(KinopoiskFilm::getName)
                .collect(Collectors.toList());
    }

    public List<KinopoiskFilm> getRating() {
        return kinopoiskFilms.stream().filter(kinopoiskFilm -> kinopoiskFilm.getRating().getKp() > 8)
                .collect(Collectors
                        .toList());
    }

    public Map<Integer, List<KinopoiskFilm>> getFilmsByYears() {
        return kinopoiskFilms.stream().collect(Collectors.groupingBy(KinopoiskFilm::getYear));
    }

    public double getAverageRating() {
        return getAverage(kinopoiskFilm -> kinopoiskFilm.getRating().getKp());
    }

    public double getAverageLength() {
        return getAverage(KinopoiskFilm::getMovieLength);
    }

    private <T extends Number> double getAverage(Function<KinopoiskFilm, T> function) {
        return kinopoiskFilms.stream().map(function).mapToDouble(Number::doubleValue).average()
                .orElseThrow(() -> new FilmsException("Фильмы не загружены."));
    }

    public void test() {
        getByFilter(Map.of("byRating", 8,
                "byLength,", 90
        ));
    }
}
