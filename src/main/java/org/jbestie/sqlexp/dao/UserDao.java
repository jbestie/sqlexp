package org.jbestie.sqlexp.dao;

import org.jbestie.sqlexp.model.User;

/**
 * User's DAO to get/set user details
 * 
 * @author bestie
 *
 */
public interface UserDao {
    
    /**
     * Creates the user in database
     * 
     * @param user - {@link User} details to store
     * 
     * @return id of created record
     */
    Long createUser(User user);
    
    
    /**
     * Retrieves the user from db by id
     * 
     * @param id - user's id
     * 
     * @return {@link User} details
     */
    User getUser(Long id);
    
    
    /**
     * Updates the user details
     * 
     * @param user - {@link User} details to update
     */
    void updateUser(User user);


    /**
     * Deletes the user by id
     * 
     * @param id - user's id
     */
    void deleteUser(Long id);
}
