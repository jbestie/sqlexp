package org.jbestie.sqlexp.service;

import java.util.List;

import org.jbestie.sqlexp.dao.SqlExpDao;
import org.jbestie.sqlexp.model.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SqlExpServiceImpl implements SqlExpService {

    @Autowired
    private SqlExpDao sqlExpDao;
    
    /**
     * {@inheritDoc}}
     */
    @Override
    public QueryResult performUsersQuery(Long questionId, String query) {
        QueryResult usersResult = sqlExpDao.performQuery(query);
        return usersResult;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public QueryResult performCorrectQuery(Long questionId) {
        String correctQuery = sqlExpDao.getCorrectQuery(questionId);
        QueryResult correctResult = sqlExpDao.performQuery(correctQuery);
        return correctResult;
    }
    
    /**
     * {@inheritDoc}}
     */
    @Override
    public String getTaskDescription(Long questionId) {
        return sqlExpDao.getTaskDescription(questionId);
    }
    
    /**
     * {@inheritDoc}}
     */
    @Override
    public List<String> getAllQuestionNames() {
        return sqlExpDao.getAllQuestionNames();
    }
}
