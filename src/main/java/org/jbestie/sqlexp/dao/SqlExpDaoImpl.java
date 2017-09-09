package org.jbestie.sqlexp.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbestie.sqlexp.model.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SqlExpDaoImpl implements SqlExpDao {

    
    private static final String SELECT_QUESTION_DESCRIPTION = "SELECT description FROM TASK WHERE id = :questionId";

    private static final String SELECT_QUERY_FOR_CORRECT_RESULT = "SELECT query FROM TASK WHERE id = :questionId";
    
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public QueryResult performQuery(String query) {
        return jdbcTemplate.query(query, (rs) -> {
            List<String> columns = new ArrayList<String>();
            for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
                columns.add(rs.getMetaData().getColumnName(i));
            }
            
            List<List<String>> values = new ArrayList<List<String>>();
            while(rs.next()) {
                List<String> value = new ArrayList<>();
                for (String column : columns) {
                    value.add(rs.getString(column));
                }
                values.add(value);
            }
            
            return new QueryResult(columns, values);
        });
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String getCorrectQuery(Long questionId) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("questionId", questionId);
        
        return jdbcTemplate.queryForObject(SELECT_QUERY_FOR_CORRECT_RESULT, queryMap, (rs, row) -> {return rs.getString("query");});
    }
    

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTaskDescription(Long questionId) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("questionId", questionId);
        
        return jdbcTemplate.queryForObject(SELECT_QUESTION_DESCRIPTION, queryMap, (rs, row) -> {return rs.getString("description");});
    }
}
