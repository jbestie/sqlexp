package org.jbestie.sqlexp;

import java.time.LocalDateTime;

import org.jbestie.sqlexp.config.SqlExpConfiguration;
import org.jbestie.sqlexp.dao.UserDao;
import org.jbestie.sqlexp.enums.Role;
import org.jbestie.sqlexp.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SqlExpConfiguration.class})
@Transactional
//@ActiveProfiles("test")
public class UserDaoTest {

    @Autowired
    UserDao userDao;
    
    @Test
    @Rollback
    public void testCreateGetDeleteUser() {
        User user = new User(-1L, "login", "password", "email@test.mail", LocalDateTime.now(), Role.ROLE_USER, true);
        
        Long id = userDao.createUser(user);
        user.setId(id);
        
        User storedUser = userDao.getUser(id);
        Assert.assertEquals("Objects have to be equal!", user, storedUser);
        
        user.setActive(false);
        userDao.updateUser(user);
        
        storedUser = userDao.getUser(id);
        Assert.assertEquals("Objects have to be equal!", user, storedUser);
        
        userDao.deleteUser(id);
        
        try {
            userDao.getUser(id);
            Assert.fail("Has to be failed on selecting");
        } catch (EmptyResultDataAccessException ex) {
            // we're good because no records
        }
        
    }
}
