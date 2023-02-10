package ru.kinopoisk.server.example.Model;

public class KinopoiskFilm {
    private final Rating rating;
    private final int id;
    private final String name;
    private final String description;
    private final int year;
    private final int movieLength;
    private final String shortDescription;

    public KinopoiskFilm(Rating rating, int id, String name, String description, int year, int movieLength,
                         String shortDescription) {
        this.rating = rating;
        this.id = id;
        this.name = name;
        this.description = description;
        this.year = year;
        this.movieLength = movieLength;
        this.shortDescription = shortDescription;
    }

    public Rating getRating() {
        return rating;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getYear() {
        return year;
    }

    public int getMovieLength() {
        return movieLength;
    }

    public String getShortDescription() {
        return shortDescription;
    }
}
