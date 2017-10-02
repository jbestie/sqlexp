package org.jbestie.sqlexp.service;

import org.jbestie.sqlexp.model.Task;

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
}
