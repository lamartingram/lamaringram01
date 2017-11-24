/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adm.virtualrealitygladiators.dao;

import com.adm.virtualrealitygladiators.dto.Image;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author adam-
 */
public class VRGImageDaoJdbcImpl implements VRGImageDao{

    private JdbcTemplate template;

    public void setJdbcTemplate(JdbcTemplate template) {
        this.template = template;
    }
    
    private static final String INSERT_IMAGE
            = "insert into images (path, description) "
            + "values (?, ?)";
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addImage(Image image) {
        template.update(INSERT_IMAGE,
                image.getPath(),
                image.getDescription());
        int imageId = template.queryForObject("select LAST_INSERT_ID()", Integer.class);
        image.setImageId(imageId);
    }
    
    private static final String DELETE_BLOGAUTHORS
            = "delete from blogauthors where blogId = ?";
    private static final String DELETE_BLOGSTAGS
            = "delete from blogstags where blogId = ?";
    private static final String DELETE_BLOGSCATEGORIES
            = "delete from blogscategories where blogId = ?";
    private static final String DELETE_BLOG_FOR_IMAGE
            = "delete from blogs where imageId = ?";
    private static final String DELETE_IMAGE
            = "delete from images where imageId = ?";
    private static final String DELETE_COMMENTS
            = "delete from comments where blogId = ?";
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void removeImage(int imageId, int blogId) {
        template.update(DELETE_BLOGAUTHORS, blogId);
        template.update(DELETE_BLOGSTAGS, blogId);
        template.update(DELETE_BLOGSCATEGORIES, blogId);
        template.update(DELETE_COMMENTS, blogId);
        template.update(DELETE_BLOG_FOR_IMAGE, imageId);
        template.update(DELETE_IMAGE, imageId);
    }

    private static final String UPDATE_IMAGE
            = "update images set path = ?, description = ? "
            + "where imageId = ?";
    @Override
    public void updateImage(Image image) {
        template.update(UPDATE_IMAGE,
                image.getPath(),
                image.getDescription(),
                image.getImageId());
    }

    private static final String SELECT_IMAGE_BY_ID
            = "select * from images where imageId = ?";
    @Override
    public Image getImageById(int imageId) {
        try {
            return template.queryForObject(SELECT_IMAGE_BY_ID, new ImageMapper(), imageId);
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static final String SELECT_ALL_IMAGES
            = "select * from images";
    @Override
    public List<Image> getAllImages() {
        return template.query(SELECT_ALL_IMAGES, new ImageMapper());
    }
    
}
