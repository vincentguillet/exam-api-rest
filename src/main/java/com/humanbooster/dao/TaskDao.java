package com.humanbooster.dao;

import com.humanbooster.model.Task;
import org.hibernate.SessionFactory;

/**
 * TaskDao is a Data Access Object (DAO) for the Task entity.
 * It extends the GenericDaoImpl to provide CRUD operations for Task entities.
 */
public class TaskDao extends GenericDaoImpl<Task,Long> {

    /**
     * Constructor for TaskDao.
     *
     * @param sessionFactory the Hibernate SessionFactory used to create sessions
     * @param entityClass    the class type of the entity this DAO manages
     */
    public TaskDao(SessionFactory sessionFactory, Class<Task> entityClass) {
        super(sessionFactory, entityClass);
    }
}
