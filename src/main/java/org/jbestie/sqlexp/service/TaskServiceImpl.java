package org.jbestie.sqlexp.service;

import org.jbestie.sqlexp.dao.TaskDao;
import org.jbestie.sqlexp.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskDao taskDao;


    @Override
    public Long createTask(Task task) {
        return taskDao.createTask(task);
    }

    @Override
    public void deleteTask(Long id) {
        taskDao.deleteTask(id);
    }

    @Override
    public void updateTask(Task task) {
        taskDao.updateTask(task);
    }

    @Override
    public Task getTask(Long id) {
        return taskDao.getTask(id);
    }
}
