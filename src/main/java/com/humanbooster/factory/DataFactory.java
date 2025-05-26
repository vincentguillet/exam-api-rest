package com.humanbooster.factory;

import com.humanbooster.model.Task;
import com.humanbooster.service.TaskService;

import java.util.ArrayList;
import java.util.List;

/**
 * DataFactory is a utility class for creating Task instances and managing task-related operations.
 * It is primarily used for testing purposes to generate tasks with specific attributes.
 */
public class DataFactory {

    private final TaskService taskService;

    /**
     * Constructor for DataFactory.
     * Initializes the DataFactory with a TaskService instance.
     *
     * @param taskService the TaskService instance used to manage tasks
     */
    public DataFactory(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Creates a new Task instance with the specified title, description, and done status.
     * For testing purposes only
     * @param title       the title of the task
     * @param description the description of the task
     * @param done        the status of the task (true if done, false otherwise)
     * @return a new Task instance
     */
    public Task createTask(String title, String description, boolean done) {
        return new Task(title, description, false);
    }

    /**
     * Creates a list of tasks.
     * For testing purposes only
     * @param numberOfTasks the number of tasks to create
     */
    public void createMultipleTask(int numberOfTasks) {
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < numberOfTasks; i++) {
            tasks.add(i, new Task("Task " + (i + 1), "Description for task " + (i + 1), false));
        }
        for (Task task : tasks) {
            taskService.createTask(task);
            System.out.println("Created task: " + task.getTitle() + " - " + task.getDescription());
        }
    }
}
