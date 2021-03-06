package org.jbestie.sqlexp.service;

import org.jbestie.sqlexp.model.Task;
import org.jbestie.sqlexp.model.TaskCategory;

import java.util.List;
import java.util.Map;

public interface TaskService {
    /**
     * Stores the new task in DB
     *
     * @param task - {@link Task} task to store
     *
     * @return id of stored record
     */
    Long createTask(Task task);

    /**
     * Deletes the task from db
     *
     * @param id - record id to delete
     */
    void deleteTask(Long id);


    /**
     * Updates the task record in DB
     *
     * @param task - {@link Task} to update
     */
    void updateTask(Task task);


    /**
     * Retrieves the question from DB
     *
     * @param id - id of question
     *
     * @return - {@link Task} details from DB
     */
    Task getTask(Long id);

    /**
     * Retrieves the all tasks list from DB
     *
     * @return {@link List} with all {@link Task}
     */
    List<Task> getAllTasks();

    /**
     * Retrieves all available categories
     *
     * @return {@link List} with all categories mapped to id
     */
    List<TaskCategory> getAllCategories();

    /**
     * Creates the task category
     *
     * @param taskCategory task category to create
     */
    void createCategory(TaskCategory taskCategory);

    /**
     * Retrieves the all tasks for specified category
     *
     * @param categoryId category id
     *
     * @return {@link List} of {@link Task} for specified category
     */
    List<Task> getAllTasksInCategory(Long categoryId);
}
