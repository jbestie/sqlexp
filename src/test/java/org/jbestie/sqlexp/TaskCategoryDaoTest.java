package org.jbestie.sqlexp;

import org.jbestie.sqlexp.config.SqlExpConfiguration;
import org.jbestie.sqlexp.dao.TaskCategoryDao;
import org.jbestie.sqlexp.model.TaskCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SqlExpConfiguration.class})
@Transactional
public class TaskCategoryDaoTest {
    @Autowired
    TaskCategoryDao taskCategoryDao;

    @Test
    @Rollback
    public void testCreateAndDeleteTask() {
        int tasksQty = taskCategoryDao.getAllTaskCategories().size();

        TaskCategory category = new TaskCategory(-1L, "Name");
        Long id = taskCategoryDao.createTaskCategory(category);
        category.setId(id);

        TaskCategory storedTask = taskCategoryDao.getTaskCategory(id);
        assertEquals("Should be equals", category, storedTask);

        List<TaskCategory> taskList = taskCategoryDao.getAllTaskCategories();
        assertEquals("Size should be equal!", tasksQty + 1, taskList.size());

        category.setName("Another");
        taskCategoryDao.updateTaskCategory(category);

        storedTask = taskCategoryDao.getTaskCategory(id);
        assertEquals("Should be equals!", category, storedTask);

        try {
            taskCategoryDao.deleteTaskCategory(id);
            taskCategoryDao.getTaskCategory(id);
            fail("Should throw exception when no records!");
        } catch (Exception ex) {
            // all is ok
        }
    }
}
