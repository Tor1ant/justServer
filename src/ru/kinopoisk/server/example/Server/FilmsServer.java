package ru.kinopoisk.server.example.Server;

import com.sun.net.httpserver.HttpServer;
import ru.kinopoisk.server.example.Managers.FilmsManager;

import java.io.IOException;
import java.net.InetSocketAddress;

public class FilmsServer {
    private static Integer port;
    public static void startFilmsServer(FilmsManager filmsManager) throws IOException {
        HttpServer httpServer = HttpServer.create();
        httpServer.bind(new InetSocketAddress(port), 0);
        httpServer.createContext("/films/load", new FilmsLoadHandler(filmsManager));
        httpServer.start();
    }
}
