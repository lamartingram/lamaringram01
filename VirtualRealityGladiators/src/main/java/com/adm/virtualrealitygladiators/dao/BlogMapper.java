/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adm.virtualrealitygladiators.dao;

import com.adm.virtualrealitygladiators.dto.Blog;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author adam-
 */
public class BlogMapper implements RowMapper<Blog>{

    @Override
    public Blog mapRow(ResultSet rs, int i) throws SQLException {
        Blog blog = new Blog();
        blog.setBlogId(rs.getInt("blogId"));
        blog.setTitle(rs.getString("title"));
        blog.setDate(rs.getTimestamp("blogDate").toLocalDateTime().toLocalDate());
        blog.setPost(rs.getString("post"));
        blog.setApproved(rs.getBoolean("approved"));
        blog.setViewed(rs.getBoolean("viewed"));
        blog.setSummary(rs.getString("summary"));
        return blog;
    }
    
}
