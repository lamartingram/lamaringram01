/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adm.virtualrealitygladiators.dao;

import com.adm.virtualrealitygladiators.dto.User;
import java.util.List;

/**
 *
 * @author adam-
 */
public interface VRGUserDao {
    
    public void addUser(User user);
    
    public void removeUser(int userId);
    
    public void updateUser(User user);
    
    public User getUserById(int userId);
    
    public List<User> getAllUsers();
    
    public User getUserByBlogId(int blogId);
    
    public User getUserByUserName(String userName);
    
}
