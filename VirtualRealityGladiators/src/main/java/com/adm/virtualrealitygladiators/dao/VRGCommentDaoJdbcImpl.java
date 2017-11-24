/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adm.virtualrealitygladiators.dao;

import com.adm.virtualrealitygladiators.dto.Comment;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author adam-
 */
public class VRGCommentDaoJdbcImpl implements VRGCommentDao {

    private JdbcTemplate template;

    public void setJdbcTemplate(JdbcTemplate template) {
        this.template = template;
    }

    private static final String INSERT_COMMENT
            = "insert into comments (blogId, comment, flagged, author, commentdate ) "
            + "values (?, ?, ?, ?, ?)";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addComment(Comment comment, int blogId) {
        template.update(INSERT_COMMENT,
                blogId,
                comment.getComment(),
                comment.isFlagged(),
                comment.getAuthor(),
                comment.getDate().toString());
        int commentId = template.queryForObject("select LAST_INSERT_ID()", Integer.class);
        comment.setCommentId(commentId);
    }

    private static final String DELETE_COMMENT
            = "delete from comments "
            + "where commentId = ?";

    @Override
    //@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void removeComment(int commentId) {
        template.update(DELETE_COMMENT, commentId);
    }

    private static final String UPDATE_COMMENT
            = "update comments set comment = ?, flagged = ?, author = ?, commentdate = ? "
            + "where commentId = ?";

    @Override
    public void updateComment(Comment comment) {
        template.update(UPDATE_COMMENT,
                comment.getComment(),
                comment.isFlagged(),
                comment.getAuthor(),
                comment.getDate().toString(),
                comment.getCommentId());
    }

    private static final String SELECT_COMMENT_BY_ID
            = "select * from comments where commentId = ?";

    @Override
    public Comment getCommentById(int commentId) {
        try {
            return template.queryForObject(SELECT_COMMENT_BY_ID, new CommentMapper(), commentId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static final String SELECT_ALL_COMMENTS
            = "select * from comments";

    @Override
    public List<Comment> getAllComments() {
        return template.query(SELECT_ALL_COMMENTS, new CommentMapper());
    }

    private static final String SELECT_UNFLAGGED_COMMENTS_BY_BLOGID
            = "select * from comments where blogId = ? AND flagged = false "
            + "order by commentDate desc";

    @Override
    public List<Comment> getUnflaggedCommentsByBlogId(int blogId) {
        return template.query(SELECT_UNFLAGGED_COMMENTS_BY_BLOGID, new CommentMapper(), blogId);
    }
    
    private static final String SELECT_FLAGGED_COMMENTS_BY_BLOGID
            = "select * from comments where blogId = ? AND flagged = true";
    @Override
    public List<Comment> getFlaggedCommentsByBlogId(int blogId) {
        return template.query(SELECT_FLAGGED_COMMENTS_BY_BLOGID, new CommentMapper(), blogId);
    }

    private static final String SELECT_FLAGGED_COMMENTS
            = "select * from comments where flagged = true";
    @Override
    public List<Comment> getAllFlaggedComments() {
        return template.query(SELECT_FLAGGED_COMMENTS, new CommentMapper());
    }

}
