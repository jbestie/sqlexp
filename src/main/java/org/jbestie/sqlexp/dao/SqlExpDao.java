package org.jbestie.sqlexp.dao;

import java.util.List;

import org.jbestie.sqlexp.model.QueryResult;

public interface SqlExpDao {
    
    /**
     * Runs the query and concat the result into single string
     * 
     * @param query - query to run
     * 
     * @return {@link QueryResult} result
     */
    QueryResult performQuery(String query);
    
    
    /**
     * Retrieves the query from db which allows to get correct resultset
     * 
     * @param questionId - id of question
     * 
     * @return result
     */
    String getCorrectQuery(Long questionId);
    
    
    /**
     * Retrieves the description for task
     * 
     * @param questionId - id of question
     * 
     * @return task description
     */
    String getTaskDescription(Long questionId);
    
    
    /**
     * Retrieves the all questions from DB
     * 
     * @return {@link List} of question names
     */
    List<String> getAllQuestionNames();
}
