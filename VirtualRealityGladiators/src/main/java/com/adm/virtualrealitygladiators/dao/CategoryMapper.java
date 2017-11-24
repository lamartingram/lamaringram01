/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adm.virtualrealitygladiators.dao;

import com.adm.virtualrealitygladiators.dto.Category;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author adam-
 */
public class CategoryMapper implements RowMapper<Category>{

    @Override
    public Category mapRow(ResultSet rs, int i) throws SQLException {
        Category cat = new Category();
        cat.setCategoryId(rs.getInt("categoryId"));
        cat.setCategory(rs.getString("category"));
        return cat;
    }
    
}
