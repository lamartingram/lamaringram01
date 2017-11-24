/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adm.virtualrealitygladiators.dao;

import com.adm.virtualrealitygladiators.dto.Comment;
import java.util.List;

/**
 *
 * @author adam-
 */
public interface VRGCommentDao {
    
    public void addComment(Comment comment, int blogId);
    
    public void removeComment(int commentId);
    
    public void updateComment(Comment comment);
    
    public Comment getCommentById(int commentId);
    
    public List<Comment> getAllComments();
    
    public List<Comment> getUnflaggedCommentsByBlogId(int blogId);
    
    public List<Comment> getFlaggedCommentsByBlogId(int blogId);
    
    public List<Comment> getAllFlaggedComments();
    
}
