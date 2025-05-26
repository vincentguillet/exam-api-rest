package com.humanbooster.config;

import com.humanbooster.App;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.reflections.Reflections;

import jakarta.persistence.Entity;

/**
 * HibernateConfig is responsible for configuring the Hibernate SessionFactory.
 * It checks if the application is running in a local environment or a Docker environment
 * and sets up the database connection accordingly.
 */
public class HibernateConfig {

    /**
     * Returns a SessionFactory based on the environment configuration.
     * If LOCAL_ENVIRONMENT is true, it uses a local MySQL database configuration.
     * Otherwise, it uses the default Hibernate configuration.
     *
     * @return SessionFactory
     */
    public SessionFactory getSessionFactory() {

        SessionFactory sessionFactory;

        // If the application is running in a local environment, use a specific MySQL configuration
        if (App.LOCAL_ENVIRONMENT) {

            // Create a new Configuration instance for local MySQL database
            Configuration config = new Configuration()
                    .setProperty("hibernate.connection.url", "jdbc:mysql://127.0.0.1:3306/exam-api-rest")
                    .setProperty("hibernate.connection.username", "root")
                    .setProperty("hibernate.connection.password", "root")
                    .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                    .setProperty("hibernate.hbm2ddl.auto", "update")
                    .setProperty("hibernate.show_sql", "false")
                    .setProperty("hibernate.format_sql", "true");

            // Add annotated classes dynamically using Reflections
            Reflections reflections = new Reflections("com.humanbooster.model");
            for (Class<?> clazz : reflections.getTypesAnnotatedWith(Entity.class)) {
                config.addAnnotatedClass(clazz);
            }

            // Build the SessionFactory using the Configuration
            return sessionFactory = config.buildSessionFactory();

        } else {
            // If the application is running in a Docker environment, use the default Hibernate configuration (resources/hibernate.cfg.xml)
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();

            Metadata metadata = new MetadataSources(registry).buildMetadata();
            return sessionFactory = metadata.buildSessionFactory();
        }
    }
}

