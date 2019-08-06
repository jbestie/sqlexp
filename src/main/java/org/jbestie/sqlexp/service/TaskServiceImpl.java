package org.jbestie.sqlexp.service;

import org.jbestie.sqlexp.dao.TaskCategoryDao;
import org.jbestie.sqlexp.dao.TaskDao;
import org.jbestie.sqlexp.model.Task;
import org.jbestie.sqlexp.model.TaskCategory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TaskServiceImpl implements TaskService {

    final TaskDao taskDao;
    final TaskCategoryDao taskCategoryDao;

    public TaskServiceImpl(TaskDao taskDao, TaskCategoryDao taskCategoryDao) {
        this.taskDao = taskDao;
        this.taskCategoryDao = taskCategoryDao;
    }


    @Override
    @Transactional
    public Long createTask(Task task) {
        return taskDao.createTask(task);
    }

    @Override
    @Transactional
    public void deleteTask(Long id) {
        taskDao.deleteTask(id);
    }

    @Override
    @Transactional
    public void updateTask(Task task) {
        taskDao.updateTask(task);
    }

    @Override
    public Task getTask(Long id) {
        return taskDao.getTask(id);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskDao.getAllTasks();
    }

    @Override
    public List<TaskCategory>  getAllCategories() {
        return taskCategoryDao.getAllTaskCategories();
    }
}
