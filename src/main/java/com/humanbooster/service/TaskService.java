package com.humanbooster.service;

import com.humanbooster.dao.TaskDao;
import com.humanbooster.model.Task;

import java.util.List;

/**
 * Service class for managing tasks.
 * This class provides methods to create, read, update, and delete tasks in the database.
 */
public record TaskService(TaskDao taskDao) {

    /**
     * Creates a new task in the database.
     * @param task The task to create
     */
    public void createTask(Task task) {
        taskDao.create(task);
    }

    /**
     * Retrieves a task by its ID.
     * @param id The ID of the task to retrieve
     */
    public void getTaskById(Long id) {
        taskDao.read(id);
    }

    /**
     * Updates an existing task in the database.
     * @param id The ID of the task to update
     */
    public void updateTask(Long id) {
        taskDao.update(id);
    }

    /**
     * Deletes a task by its ID.
     * @param id The ID of the task to delete
     */
    public void deleteTask(Long id) {
        taskDao.delete(id);
    }

    /**
     * Retrieves all tasks from the database.
     * @return List of all tasks
     */
    public List<Task> getAllTasks() {
        return taskDao.getAll();
    }
}
