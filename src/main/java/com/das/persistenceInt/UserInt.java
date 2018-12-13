/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.das.persistenceInt;

import com.das.model.User;

/**
 *
 * @author diego
 */
public interface UserInt {
    
    public boolean insertUser( User user ) throws Exception;
    public User findUser(User user)throws Exception;
    public boolean deleteUser (User user) throws Exception;
    public boolean updateUser (User user) throws Exception;
    
}
