package org.jbestie.sqlexp.dao;

import java.util.HashMap;
import java.util.Map;

import org.jbestie.sqlexp.enums.Role;
import org.jbestie.sqlexp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    private static final String UPDATE_USER_QUERY = "UPDATE USERS SET login = :login, password = :password, email = :email, role = :role, active = :active WHERE id = :id";

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;
    
    private static final String CREATE_USER_QUERY = "INSERT INTO USERS(id, login, password, email, registration_date, role, active) VALUES (nextval('seq_user'), :login, :password, :email, :registration_date, :role, true) RETURNING id";
    private static final String SELECT_USER_QUERY = "SELECT id, login, password, email, registration_date, role, active FROM USERS WHERE id = :id";

    /**
     * {@inheritDoc}
     */
    @Override
    public Long createUser(User user) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("login", user.getLogin());
        paramMap.put("password", user.getPassword());
        paramMap.put("email", user.getEmail());
        paramMap.put("registration_date", user.getRegistrationDate());
        paramMap.put("role", user.getRole().ordinal());
        
        return jdbcTemplate.queryForObject(CREATE_USER_QUERY, paramMap, Long.class);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public User getUser(Long id) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        
        return jdbcTemplate.queryForObject(SELECT_USER_QUERY, paramMap, (rs, rownum) -> {
            return new User(rs.getLong("id"), rs.getString("login"), rs.getString("password"), rs.getString("email"), rs.getTimestamp("registration_date").toLocalDateTime(), Role.values()[rs.getInt("role")], rs.getBoolean("active"));
        });
    }

    
    /**
     * {@inheritDoc}
     */
    @Override
    public void updateUser(User user) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", user.getId());
        paramMap.put("login", user.getLogin());
        paramMap.put("password", user.getPassword());
        paramMap.put("email", user.getEmail());
        paramMap.put("role", user.getRole().ordinal());
        paramMap.put("active", user.isActive());
        
        jdbcTemplate.update(UPDATE_USER_QUERY, paramMap);
    }

    
    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteUser(Long id) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        
        jdbcTemplate.update("DELETE FROM USERS WHERE id = :id", paramMap);
    }
}
