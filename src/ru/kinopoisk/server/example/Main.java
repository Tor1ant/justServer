package ru.kinopoisk.server.example;

import ru.kinopoisk.server.example.Managers.FilmsManager;
import ru.kinopoisk.server.example.Server.FilmsServer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        KinopoiskClient kinopoiskClient = new KinopoiskClient("4VYAW5J-GJHMF6N-KKJ94RW-DYZX79Z");
        FilmsManager filmsManager = new FilmsManager(kinopoiskClient);
        FilmsServer.startFilmsServer(filmsManager);
    }
}
