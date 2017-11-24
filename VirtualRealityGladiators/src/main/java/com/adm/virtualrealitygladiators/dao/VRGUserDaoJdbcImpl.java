/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adm.virtualrealitygladiators.dao;

import com.adm.virtualrealitygladiators.dto.User;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author adam-
 */
public class VRGUserDaoJdbcImpl implements VRGUserDao {

    private JdbcTemplate template;

    public void setJdbcTemplate(JdbcTemplate template) {
        this.template = template;
    }

    private static final String INSERT_USER
            = "insert into users (userName, userPassword, enabled, authority) "
            + "values (?, ?, ?, ?)";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addUser(User user) {
        template.update(INSERT_USER,
                user.getUserName(),
                user.getPassword(),
                user.isEnabled(),
                user.getAuthority());
        int userId = template.queryForObject("select LAST_INSERT_ID()", Integer.class);
        user.setUserId(userId);
    }

    private static final String DELETE_USER
            = "delete from users where userId = ?";
    private static final String DELETE_BLOGS_BY_USER
            = "delete from blogs where userId = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void removeUser(int userId) {
        template.update(DELETE_USER, userId);
        template.update(DELETE_BLOGS_BY_USER, userId);
    }

    private static final String UPDATE_USER
            = "update users set userName = ?, userPassword = ?, enabled = ?, authority = ? "
            + "where userId = ?";

    @Override
    public void updateUser(User user) {
        template.update(UPDATE_USER,
                user.getUserName(),
                user.getPassword(),
                user.isEnabled(),
                user.getAuthority(),
                user.getUserId());
    }

    private static final String SELECT_USER_BY_ID
            = "select * from users where userId = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public User getUserById(int userId) {
        try {
            User user = template.queryForObject(SELECT_USER_BY_ID, new UserMapper(), userId);
            return user;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static final String SELECT_ALL_USERS
            = "select * from users";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<User> getAllUsers() {
        List<User> users = template.query(SELECT_ALL_USERS, new UserMapper());
        return users;
    }

    private static final String SELECT_USER_BY_BLOG_ID
            = "select u.userId, u.userName, u.userPassword, u.enabled, u.authority "
            + "from users u inner join blogs b on b.userId = u.userId "
            + "where blogId = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public User getUserByBlogId(int blogId) {
        try {
            User user = template.queryForObject(SELECT_USER_BY_BLOG_ID, new UserMapper(), blogId);
            return user;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static final String SELECT_USER_BY_USERNAME
            = "select * from users where username =?";
    @Override
    public User getUserByUserName(String userName) {
        try {
            return template.queryForObject(SELECT_USER_BY_USERNAME, new UserMapper(), userName);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
   
}
