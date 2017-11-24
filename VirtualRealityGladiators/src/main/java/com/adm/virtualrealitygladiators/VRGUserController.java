/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adm.virtualrealitygladiators;

import com.adm.virtualrealitygladiators.dao.VRGBlogDao;
import com.adm.virtualrealitygladiators.dao.VRGCategoryDao;
import com.adm.virtualrealitygladiators.dao.VRGCommentDao;
import com.adm.virtualrealitygladiators.dao.VRGImageDao;
import com.adm.virtualrealitygladiators.dao.VRGTagDao;
import com.adm.virtualrealitygladiators.dao.VRGUserDao;
import com.adm.virtualrealitygladiators.dto.User;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author adam-
 */
@Controller
public class VRGUserController {

    private VRGBlogDao blogDao;
    private VRGCommentDao commentDao;
    private VRGCategoryDao categoryDao;
    private VRGTagDao tagDao;
    private VRGImageDao imageDao;
    private VRGUserDao userDao;

    @Inject
    public VRGUserController(VRGBlogDao blogDao,
            VRGCommentDao commentDao, VRGCategoryDao categoryDao, VRGTagDao tagDao, VRGImageDao imageDao, VRGUserDao userDao) {
        this.blogDao = blogDao;
        this.commentDao = commentDao;
        this.categoryDao = categoryDao;
        this.tagDao = tagDao;
        this.imageDao = imageDao;
        this.userDao = userDao;
    }
    
    @RequestMapping(value = "/adminUsers" , method = {RequestMethod.GET, RequestMethod.POST})
    public String displayUsersDashboard(Model model) {
        List<User> users = userDao.getAllUsers();
        model.addAttribute("users" , users);
        model.addAttribute("categories", categoryDao.getAllCategories());
        return "users";
    }
    
    @RequestMapping(value = "/displayAddUser", method = RequestMethod.GET)
    public String displayAddUser(Model model) {
        model.addAttribute("categories", categoryDao.getAllCategories());
        return "addUser";
    }
    
    @RequestMapping(value = "/addUser" , method = RequestMethod.POST)
    public String addUser(HttpServletRequest request, Model model) {
        String username = request.getParameter("addUserName");
        String password = request.getParameter("addUserPassword");
        String enabled = request.getParameter("addUserEnabled");
        String authority = request.getParameter("addUserAuthority");
        List<String> errorMessages = new ArrayList<>();
        boolean hasErrors = false;
        User user = new User();
        if (username == null || username.isEmpty()) {
            String em = "Please enter a username";
            errorMessages.add(em);
            hasErrors = true;
        }
        user.setUserName(username);
        
        if (password == null || password.isEmpty()) {
            String em = "Please enter a password";
            errorMessages.add(em);
            hasErrors = true;
        }
        user.setPassword(password);
        
        if (!(enabled == null)) {
            user.setEnabled(true);
        } else {
            user.setEnabled(false);
        }
        
        if (authority.equalsIgnoreCase("1")) {
            user.setAuthority("ROLE_ADMIN");
        } else if (authority.equalsIgnoreCase("2")) {
            user.setAuthority("ROLE_EDITOR");
        } else if (authority.equalsIgnoreCase("3")) {
            user.setAuthority("ROLE_MODERATOR");
        } else {
            String em = "Authority entered does not match our system. Please select an option from the drop-down list.";
            errorMessages.add(em);
            hasErrors = true;
        }
        if (hasErrors) {
            model.addAttribute("errorMessages" , errorMessages);
            return "addUser";
        }
        userDao.addUser(user);
        model.addAttribute("users" , userDao.getAllUsers());
        model.addAttribute("categories", categoryDao.getAllCategories());
        return "users";
    }
    
    @RequestMapping(value = "/displayEditUser/{userId}", method = RequestMethod.GET)
    public String displayEditUser(@PathVariable("userId") int id, Model model) {
        User user = userDao.getUserById(id);
        model.addAttribute("user" , user);
        model.addAttribute("categories", categoryDao.getAllCategories());
        return "editUser";
    }
    
    @RequestMapping(value = "/editUser" , method = RequestMethod.POST)
    public String editUser(HttpServletRequest request, Model model) {
        String idParam = request.getParameter("editUserId");
        String username = request.getParameter("editUserName");
        String enabled = request.getParameter("editUserEnabled");
        String authority = request.getParameter("editUserAuthority");
        List<String> errorMessages = new ArrayList<>();
        boolean hasErrors = false;
        int userId = 0;
        try {
            userId = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            String em = "Something went wrong. Please contact your system administrator if problem persists";
            errorMessages.add(em);
            hasErrors = true;
        }
        User user = userDao.getUserById(userId);
        if (username == null || username.isEmpty()) {
            String em = "Please enter a username";
            errorMessages.add(em);
            hasErrors = true;
        }
        user.setUserName(username);
        if (!(enabled == null)) {
            user.setEnabled(true);
        } else {
            user.setEnabled(false);
        }
        if (authority.equals("1")) {
            user.setAuthority("ROLE_ADMIN");
        } else if (authority.equals("2")) {
            user.setAuthority("ROLE_EDITOR");
        } else if (authority.equals("3")) {
            user.setAuthority("ROLE_MODERATOR");
        } else {
            String em = "Authority entered does not match our system. Please select an option from the drop-down list.";
            errorMessages.add(em);
            hasErrors = true;
        }
        if (hasErrors) {
            model.addAttribute("errorMessages" , errorMessages);
            return "editUser";
        }
        userDao.updateUser(user);
        model.addAttribute("users" , userDao.getAllUsers());
        model.addAttribute("categories", categoryDao.getAllCategories());
        return "users";
    }
    
    @RequestMapping(value = "/deleteUser/{userId}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("userId") int id, Model model) {
        userDao.removeUser(id);
        model.addAttribute("users" , userDao.getAllUsers());
        model.addAttribute("categories", categoryDao.getAllCategories());
        return "users";
    }
}
