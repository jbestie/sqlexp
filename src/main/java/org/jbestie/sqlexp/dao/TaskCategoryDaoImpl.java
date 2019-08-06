package org.jbestie.sqlexp.dao;

import org.jbestie.sqlexp.model.TaskCategory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TaskCategoryDaoImpl implements TaskCategoryDao {

    public static final String CREATE_CATEGORY_QUERY = "INSERT INTO TASK_CATEGORY(ID, NAME) VALUES(nextval('seq_task_category'), :name) RETURNING id";
    public static final String DELETE_CATEGORY_QUERY = "DELETE FROM TASK_CATEGORY WHERE id=:id";
    public static final String SELECT_CATEGORY_QUERY = "SELECT id, name FROM TASK_CATEGORY WHERE id = :id";
    public static final String UPDATE_CATEGORY_QUERY = "UPDATE TASK_CATEGORY SET name = :name WHERE id = :id";
    public static final String SELECT_ALL_CATEGORIES_QUERY = "SELECT id, name FROM TASK_CATEGORY ORDER BY id";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public TaskCategoryDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long createTaskCategory(TaskCategory taskCategory) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", taskCategory.getName());

        return jdbcTemplate.queryForObject(CREATE_CATEGORY_QUERY, paramMap, Long.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteTaskCategory(Long categoryId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", categoryId);

        int changedRows = jdbcTemplate.update(DELETE_CATEGORY_QUERY, paramMap);
        if (changedRows != 1) {
            throw  new IllegalStateException("Incorrect rows quantity have been deleted: " + changedRows);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateTaskCategory(TaskCategory category) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", category.getId());
        paramMap.put("name", category.getName());

        int changedRows = jdbcTemplate.update(UPDATE_CATEGORY_QUERY, paramMap);
        if (changedRows != 1) {
            throw  new IllegalStateException("We update " + changedRows + " rows instead of 1!");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskCategory getTaskCategory(Long id) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);

        return jdbcTemplate.queryForObject(SELECT_CATEGORY_QUERY, paramMap, (rs, rowNum) ->
                new TaskCategory(rs.getLong("id"), rs.getString("name")));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TaskCategory> getAllTaskCategories() {
        return jdbcTemplate.query(SELECT_ALL_CATEGORIES_QUERY, rs -> {
            List<TaskCategory> result = new ArrayList<>();
            while (rs.next()) {
                result.add(new TaskCategory(rs.getLong("id"), rs.getString("name")));
            }

            return result;
        });
    }
}
