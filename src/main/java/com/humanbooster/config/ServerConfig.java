package com.humanbooster.config;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 * ServerConfig is responsible for configuring and starting the Jersey server.
 * It sets up the resource packages and registers the Jackson feature for JSON processing.
 */
public class ServerConfig extends ResourceConfig {

    /**
     * Constructor for ServerConfig.
     * It initializes the resource packages and registers the Jackson feature.
     */
    public ServerConfig() {
        packages("com.humanbooster");
        register(JacksonFeature.class);
    }

    /**
     * Starts the Jersey server on port 80.
     * It creates a ServletHolder for the ServletContainer and sets up a ServletContextHandler.
     *
     * @throws Exception if there is an error starting the server
     */
    public void startServer() throws Exception {
        System.out.println("Lancement du serveur...");

        // Create a new instance of ResourceConfig
        ResourceConfig config = this;

        // Create a ServletHolder for the Jersey ServletContainer
        ServletHolder servlet = new ServletHolder(new ServletContainer(config));
        Server server = new Server(3000);

        // Set the context path and add the servlet to handle requests
        ServletContextHandler context = new ServletContextHandler(server, "/api");
        context.setServer(server);
        context.addServlet(servlet, "/*");

        try {
            server.start();
            System.out.println("Serveur démarré sur le port 3000");
            server.join();
        } catch (Exception e) {
            System.out.println("Echec lors du lancement du serveur: " + e.getMessage());
        }
    }
}
