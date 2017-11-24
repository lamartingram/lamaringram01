/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adm.virtualrealitygladiators.dao;

import com.adm.virtualrealitygladiators.dto.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author adam-
 */
public class ImageMapper implements RowMapper<Image>{

    @Override
    public Image mapRow(ResultSet rs, int i) throws SQLException {
        Image image = new Image();
        image.setImageId(rs.getInt("imageId"));
        image.setPath(rs.getString("path"));
        image.setDescription(rs.getString("description"));
        return image;
    }
    
}
