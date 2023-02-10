package ru.kinopoisk.server.example.Model;

public class Rating {
  private  final   double kp;
   private final double imdb;

    public Rating(double kp, double imdb) {
        this.kp = kp;
        this.imdb = imdb;
    }

    public double getKp() {
        return kp;
    }

    public double getImdb() {
        return imdb;
    }
}

