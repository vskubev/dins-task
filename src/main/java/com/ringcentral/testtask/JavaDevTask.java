package com.ringcentral.testtask;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import java.io.IOException;
import java.net.URI;

public class JavaDevTask {
    private static final String BASE_URI = "http://localhost:8081";

    private static HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig().packages("com.ringcentral.testtask.controllers");
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) throws IOException {
        HttpServer server = startServer();
        System.out.println(String.format("Application started with WADL available at "
                + "%s/application.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();
        server.stop();
    }
}
