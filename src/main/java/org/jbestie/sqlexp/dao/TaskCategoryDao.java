package org.jbestie.sqlexp.dao;

import org.jbestie.sqlexp.model.TaskCategory;

import java.util.List;

/**
 * Allows to deal with task's categories
 */
public interface TaskCategoryDao {

    /**
     * Stores the category to DB
     *
     * @param taskCategory task to save
     *
     * @return id of created record
     */
    Long createTaskCategory(TaskCategory taskCategory);

    /**
     * Deletes the category from DB
     *
     * @param categoryId category id
     */
    void deleteTaskCategory(Long categoryId);

    /**
     * Updates the category in DB
     *
     * @param category category to update
     */
    void updateTaskCategory(TaskCategory category);

    /**
     * Retrieves the category by id
     *
     * @param id category id
     *
     * @return {@link TaskCategory} associated with id
     */
    TaskCategory getTaskCategory(Long id);

    /**
     * Retrieves all available categories
     *
     * @return {@link List} of {@link TaskCategory}
     */
    List<TaskCategory> getAllTaskCategories();
}
