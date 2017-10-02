package org.jbestie.sqlexp;

import org.jbestie.sqlexp.config.SqlExpConfiguration;
import org.jbestie.sqlexp.dao.TaskDao;
import org.jbestie.sqlexp.model.Task;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SqlExpConfiguration.class})
@Transactional
public class TaskDaoTest {

    @Autowired
    TaskDao taskDao;

    @Test
    @Rollback
    public void testCreateAndDeleteTask() {
        Task task = new Task(-1L, 1L, "Name", "Description", "query");
        Long id = taskDao.createTask(task);
        task.setId(id);

        Task storedTask = taskDao.getTask(id);
        assertEquals("Should be equals", task, storedTask);

        task.setDescription("Another");
        taskDao.updateTask(task);

        storedTask = taskDao.getTask(id);
        assertEquals("Should be equals!", task, storedTask);

        try {
            taskDao.deleteTask(id);
            taskDao.getTask(id);
            fail("Should throw exception when no records!");
        } catch (Exception ex) {
            // all is ok
        }
    }
}
