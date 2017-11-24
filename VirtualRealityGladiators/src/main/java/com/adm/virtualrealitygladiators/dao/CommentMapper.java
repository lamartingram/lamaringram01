/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adm.virtualrealitygladiators.dao;

import com.adm.virtualrealitygladiators.dto.Comment;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author adam-
 */
public class CommentMapper implements RowMapper<Comment>{

    @Override
    public Comment mapRow(ResultSet rs, int i) throws SQLException {
        Comment com = new Comment();
        com.setCommentId(rs.getInt("commentId"));
        com.setComment(rs.getString("comment"));
        com.setFlagged(rs.getBoolean("flagged"));
        com.setAuthor(rs.getString("author"));
        com.setDate(rs.getTimestamp("CommentDate").toLocalDateTime().toLocalDate());
        return com;
    }
    
}
