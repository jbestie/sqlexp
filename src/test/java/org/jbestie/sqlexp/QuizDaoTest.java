package org.jbestie.sqlexp;


import java.util.Collections;
import java.util.List;

import org.jbestie.sqlexp.config.SqlExpConfiguration;
import org.jbestie.sqlexp.dao.QuizDao;
import org.jbestie.sqlexp.dao.TaskDao;
import org.jbestie.sqlexp.model.QueryResult;
import org.jbestie.sqlexp.model.Task;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SqlExpConfiguration.class})
@Transactional
public class QuizDaoTest {
    
    @Autowired
    QuizDao quizDao;


    @Autowired
    TaskDao taskDao;


    @Test
    public void testResultComparison() {
        Long id = taskDao.createTask(new Task(-1L, 1L, "Task 1", "Description", "SELECT * FROM EMPLOYEES"));
        String correctQuery = quizDao.getCorrectQuery(id);
        
        QueryResult usersResult = quizDao.performQuery("SELECT * FROM EMPLOYEES");
        QueryResult correctResult = quizDao.performQuery(correctQuery);
        
        Assert.assertEquals("The results have to be equals", correctResult, usersResult);

        String description = quizDao.getTaskDescription(id);
        Assert.assertNotNull("Description has not to be null!", description);

        List<String> names = quizDao.getAllQuestionNames();
        Assert.assertEquals("Has to be equals!", Collections.singletonList("Task 1"), names);

        taskDao.deleteTask(id);
    }


}
