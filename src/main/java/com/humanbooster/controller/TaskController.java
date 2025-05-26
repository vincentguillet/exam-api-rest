package com.humanbooster.controller;

import com.humanbooster.config.HibernateConfig;
import com.humanbooster.dao.GenericDao;
import com.humanbooster.dao.TaskDao;
import com.humanbooster.model.Task;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.hibernate.SessionFactory;

@Path("/tasks") // Base URL
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskController extends GenericControllerImpl<Task, Long> {

    /**
     * Default constructor for TaskController.
     * Initializes the controller with a Hibernate SessionFactory and a TaskDao.
     */
    public TaskController() {
        this(new HibernateConfig().getSessionFactory(), new TaskDao(new HibernateConfig().getSessionFactory()));
    }

    /**
     * Constructor for TaskController.
     *
     * @param sessionFactory the Hibernate SessionFactory
     * @param dao the GenericDao for Task entities
     */
    public TaskController(SessionFactory sessionFactory, GenericDao<Task, Long> dao) {
        super(sessionFactory, dao);
    }
}
