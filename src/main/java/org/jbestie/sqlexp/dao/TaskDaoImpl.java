package org.jbestie.sqlexp.dao;

import org.apache.commons.text.StringEscapeUtils;
import org.jbestie.sqlexp.model.Task;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TaskDaoImpl implements TaskDao {
    public static final String CREATE_TASK_QUERY = "INSERT INTO TASK(id, category_id, name, description, query) VALUES (nextval('seq_task'), :category, :name, :description, :query) RETURNING id";
    public static final String DELETE_TASK_QUERY = "DELETE FROM TASK WHERE id = :id";
    public static final String UPDATE_TASK_QUERY = "UPDATE TASK SET category_id = :category, name = :name, description = :description, query = :query WHERE id = :id";
    public static final String SELECT_TASK_QUERY = "SELECT id, category_id, name, description, query FROM TASK WHERE id = :id";
    public static final String SELECT_ALL_TASKS_QUERY = "SELECT id, category_id, name, description, query FROM TASK ORDER BY id";
    final NamedParameterJdbcTemplate jdbcTemplate;

    public TaskDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long createTask(Task task) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("category", task.getCategory());
        paramMap.put("name", task.getName());
        paramMap.put("description", task.getDescription());
        paramMap.put("query", task.getQuery());

        return jdbcTemplate.queryForObject(CREATE_TASK_QUERY, paramMap, Long.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteTask(Long id) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);

        int changedRows = jdbcTemplate.update(DELETE_TASK_QUERY, paramMap);
        if (changedRows != 1) {
            throw  new IllegalStateException("We deleted " + changedRows + " rows instead of 1!");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateTask(Task task) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", task.getId());
        paramMap.put("category", task.getCategory());
        paramMap.put("name", task.getName());
        paramMap.put("description", task.getDescription());
        paramMap.put("query", task.getQuery());

        int changedRows = jdbcTemplate.update(UPDATE_TASK_QUERY, paramMap);
        if (changedRows != 1) {
            throw  new IllegalStateException("We update " + changedRows + " rows instead of 1!");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task getTask(Long id) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);

        return jdbcTemplate.queryForObject(SELECT_TASK_QUERY, paramMap, (rs, rowNum) ->
                new Task(rs.getLong("id"), rs.getLong("category_id"), rs.getString("name"), rs.getString("description"), StringEscapeUtils.escapeEcmaScript(rs.getString("query"))));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Task> getAllTasks() {
        return jdbcTemplate.query(SELECT_ALL_TASKS_QUERY, rs -> {
            List<Task> result = new ArrayList<>();
            while (rs.next()) {
                result.add(new Task(rs.getLong("id"), rs.getLong("category_id"), rs.getString("name"), rs.getString("description"), rs.getString("query")));
            }

            return result;
        });
    }


}
