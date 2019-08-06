package org.jbestie.sqlexp.service;

import java.util.List;

import org.jbestie.sqlexp.model.QueryResult;

public interface QuizService {
    /**
     * Performs the user's query and compare the result with required
     * 
     * @param query - query id
     * 
     * @return user's query result and exception in case if results aren't equal
     */
    QueryResult performUsersQuery(String query);

    /**
     * Performs the correct query and compare the result with required
     * 
     * @param questionId - id of question
     * 
     * @return correct query result and exception in case if results aren't equal
     */
    QueryResult performCorrectQuery(Long questionId);
    
    
    /**
     * Retrieves the description for task
     * 
     * @param questionId - id of question
     * 
     * @return task description
     */
    String getTaskDescription(Long questionId);
    
    
    /**
     * Retrieves the list of all questions name
     * 
     * @return {@link List} of all question names
     */
    List<String> getAllQuestionNames();
}
