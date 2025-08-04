package org.prth;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.prth.config.AppConfig;

import java.io.IOException;
import java.net.URI;

public class Main {
    public static final String BASE_URI = "http://localhost:8080/api/";

    public static HttpServer startServer() {
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), new AppConfig());
    }

    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println("Server started at " + BASE_URI);
        System.in.read(); // Wait for user to press enter
        server.shutdownNow();
    }
}