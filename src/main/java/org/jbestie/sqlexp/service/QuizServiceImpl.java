package org.jbestie.sqlexp.service;

import java.util.List;

import org.jbestie.sqlexp.dao.QuizDao;
import org.jbestie.sqlexp.model.QueryResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class QuizServiceImpl implements QuizService {

    private final QuizDao quizDao;

    public QuizServiceImpl(QuizDao quizDao) {
        this.quizDao = quizDao;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public QueryResult performUsersQuery(String query) {
        return quizDao.performQuery(query);
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public QueryResult performCorrectQuery(Long questionId) {
        String correctQuery = quizDao.getCorrectQuery(questionId);
        return quizDao.performQuery(correctQuery);
    }
    
    /**
     * {@inheritDoc}}
     */
    @Override
    public String getTaskDescription(Long questionId) {
        return quizDao.getTaskDescription(questionId);
    }
    
    /**
     * {@inheritDoc}}
     */
    @Override
    public List<String> getAllQuestionNames() {
        return quizDao.getAllQuestionNames();
    }
}
