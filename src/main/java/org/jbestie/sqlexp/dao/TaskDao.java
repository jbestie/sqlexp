package org.jbestie.sqlexp.dao;

import org.jbestie.sqlexp.model.Task;

import java.util.List;

public interface TaskDao {

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
     * Retrieves the tasks by category id
     *
     * @param categoryId id of category to retrieve tasks
     *
     * @return {@link List} of {@link Task} in category
     */
    List<Task> getAllTasksInCategory(Long categoryId);
}
