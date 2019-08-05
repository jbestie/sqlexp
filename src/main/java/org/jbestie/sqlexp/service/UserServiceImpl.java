package org.jbestie.sqlexp.service;

import org.jbestie.sqlexp.dao.UserDao;
import org.jbestie.sqlexp.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Long createUser(User user) {
        return userDao.createUser(user);
    }

    
    /**
     * {@inheritDoc}
     */
    @Override
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    
    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    
    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }
    
}
