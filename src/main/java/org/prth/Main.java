package org.prth;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class Main {
    public static final String BASE_URI = "http://localhost:8080/api/";

    public static HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig().packages("org.prth.controller");
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println("Jersey app started at " + BASE_URI);
        System.out.println("Press Ctrl+C to stop the server...");
        Runtime.getRuntime().addShutdownHook(new Thread(server::shutdownNow));
        try {
            Thread.currentThread().join();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}