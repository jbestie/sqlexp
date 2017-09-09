package org.jbestie.sqlexp;


import java.util.Arrays;
import java.util.List;

import org.jbestie.sqlexp.config.SqlExpConfiguration;
import org.jbestie.sqlexp.dao.SqlExpDao;
import org.jbestie.sqlexp.model.QueryResult;
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
public class SqlExpDaoTest {
    
    @Autowired
    SqlExpDao sqlExpDao;
    
    @Test
    public void testResultComparison() {
        String correctQuery = sqlExpDao.getCorrectQuery(1L);
        
        QueryResult usersResult = sqlExpDao.performQuery("SELECT * FROM EMPLOYEES");
        QueryResult correctResult = sqlExpDao.performQuery(correctQuery);
        
        Assert.assertEquals("The results have to be equals", correctResult, usersResult);
    }


    @Test
    public void testGetDescription() {
        String description = sqlExpDao.getTaskDescription(1L);
        
        Assert.assertNotNull("Description has not to be null!", description);
    }
    
    
    @Test
    public void testGetQuestionNames() {
        List<String> names = sqlExpDao.getAllQuestionNames();
        Assert.assertEquals("Has to be equals!", Arrays.asList("Question 1"), names);
    }
}
