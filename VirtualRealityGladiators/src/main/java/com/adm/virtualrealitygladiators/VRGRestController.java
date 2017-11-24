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
import com.adm.virtualrealitygladiators.dto.Blog;
import com.adm.virtualrealitygladiators.dto.User;
import java.util.List;
import javax.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author adam-
 */
@Controller
public class VRGRestController {

    private VRGBlogDao blogDao;
    private VRGCommentDao commentDao;
    private VRGCategoryDao categoryDao;
    private VRGTagDao tagDao;
    private VRGImageDao imageDao;
    private VRGUserDao userDao;

    @Inject
    public VRGRestController(VRGBlogDao blogDao,
            VRGCommentDao commentDao, VRGCategoryDao categoryDao, VRGTagDao tagDao, VRGImageDao imageDao, VRGUserDao userDao) {
        this.blogDao = blogDao;
        this.commentDao = commentDao;
        this.categoryDao = categoryDao;
        this.tagDao = tagDao;
        this.imageDao = imageDao;
        this.userDao = userDao;
    }
    
    @RequestMapping(value = "/deleteBlog/{blogId}" , method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBlog(@PathVariable("blogId") int id){
        blogDao.removeBlog(id);
    }
    
    @RequestMapping(value = "/blogs" , method = RequestMethod.GET)
    @ResponseBody
    public List<Blog> getAllBlogs() {
        return blogDao.getAllBlogs();
    }
    
    @RequestMapping(value = "/rejectedBlogs" , method = RequestMethod.GET)
    @ResponseBody
    public List<Blog> getRejectedBlogsByUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = userDao.getUserByUserName(username);
        return blogDao.getAllRejectedBlogs(user.getUserId());
    }
    
    @RequestMapping(value = "/pendingBlogs" , method = RequestMethod.GET)
    @ResponseBody
    public List<Blog> getPendingBlogsByUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = userDao.getUserByUserName(username);
        return blogDao.getAllPendingBlogsByUser(user.getUserId());
    }
}