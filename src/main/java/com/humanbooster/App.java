package com.humanbooster;

import com.humanbooster.config.HibernateConfig;
import com.humanbooster.config.ServerConfig;
import com.humanbooster.dao.TaskDao;
import com.humanbooster.factory.DataFactory;
import com.humanbooster.model.Task;
import com.humanbooster.service.TaskService;
import org.hibernate.SessionFactory;

import java.util.List;

public class App {

    public static final boolean LOCAL_ENVIRONMENT = false; //Set to true if using local MySQL database, false if using docker

    public static void main(String[] args) {

        System.out.println("Démarrage de l'application...");

        SessionFactory sessionFactory = new HibernateConfig().getSessionFactory();

        TaskService taskService = new TaskService(new TaskDao(sessionFactory));

        cleanDatabase(taskService);

        DataFactory dataFactory = new DataFactory(taskService);
        dataFactory.createMultipleTask(5);

        try {
            ServerConfig serverConfig = new ServerConfig();
            serverConfig.startServer();
        } catch (Exception e) {
            System.out.println("Erreur lors du démarrage du serveur : " + e.getMessage());
        }

        sessionFactory.close();
        System.out.print("Fin du programme");
    }

    /**
     * Cleans the database by deleting all existing tasks.
     *
     * @param taskService the TaskService instance used to interact with the database
     */
    static void cleanDatabase(TaskService taskService) {
        List<Task> existingTasks = taskService.getAllTasks();
        existingTasks.forEach(task -> taskService.deleteTask(task.getId()));
    }
}