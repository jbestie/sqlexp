package org.jbestie.sqlexp.service;

import java.util.List;

import org.jbestie.sqlexp.dao.SqlExpDao;
import org.jbestie.sqlexp.model.QueryResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SqlExpServiceImpl implements SqlExpService {

    private final SqlExpDao sqlExpDao;

    public SqlExpServiceImpl(SqlExpDao sqlExpDao) {
        this.sqlExpDao = sqlExpDao;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public QueryResult performUsersQuery(Long questionId, String query) {
        return sqlExpDao.performQuery(query);
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public QueryResult performCorrectQuery(Long questionId) {
        String correctQuery = sqlExpDao.getCorrectQuery(questionId);
        return sqlExpDao.performQuery(correctQuery);
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
