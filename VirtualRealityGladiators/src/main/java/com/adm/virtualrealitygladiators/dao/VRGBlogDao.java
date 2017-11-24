/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adm.virtualrealitygladiators.dao;

import com.adm.virtualrealitygladiators.dto.Blog;
import com.adm.virtualrealitygladiators.dto.Category;
import com.adm.virtualrealitygladiators.dto.Tag;
import com.adm.virtualrealitygladiators.dto.User;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author adam-
 */
public interface VRGBlogDao {

    public void addBlog(Blog blog);

    public void removeBlog(int blogId);

    public void updateBlog(Blog blog);

    public Blog getBlogById(int blogId);

    public List<Blog> getAllBlogs();

    public List<Blog> getBlogsByDate(LocalDate date);

    public List<Blog> getBlogsByTag(int tagId);
    
    public Blog getMostRecentBlog();

    public List<Blog> getThreeRecentBlogs();
    
    public List<Blog> getOtherRecentBlogs();

    public List<Blog> getAllApprovedPosts();

    public List<Blog> getAllUnapprovedPosts();
    
    public List<Blog> getBlogsByCategory(Category category);
    
    public List<Blog> getApprovedBlogsByUser(int userId);
    
    public List<Blog> getAllPendingBlogsByUser(int userId);
    
    public List<Blog> getAllRejectedBlogs(int userId);
    
    public List<Blog> getAllPendingBlogs();
    
    public Blog getBlogByCommentId(int commentId);

}
