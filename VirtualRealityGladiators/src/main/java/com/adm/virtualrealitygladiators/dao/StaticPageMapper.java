/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adm.virtualrealitygladiators.dao;

import com.adm.virtualrealitygladiators.dto.StaticPage;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author adam-
 */
public class StaticPageMapper implements RowMapper<StaticPage>{

    @Override
    public StaticPage mapRow(ResultSet rs, int i) throws SQLException {
        StaticPage page = new StaticPage();
        page.setStaticPageId(rs.getInt("staticPageId"));
        page.setPost(rs.getString("post"));
        page.setTitle(rs.getString("title"));
        page.setDate(rs.getTimestamp("date").toLocalDateTime().toLocalDate());
        return page;
    }
    
}
