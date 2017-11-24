/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adm.virtualrealitygladiators.dao;

import com.adm.virtualrealitygladiators.dto.Blog;
import com.adm.virtualrealitygladiators.dto.Category;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author adam-
 */
public class VRGCategoryDaoJdbcImpl implements VRGCategoryDao{

    private JdbcTemplate template;
    
    public void setJdbcTemplate(JdbcTemplate template) {
        this.template = template;
    }
    
    private static final String INSERT_CATEGORY
            = "insert into categories (category) "
            + "values (?)";
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addCategory(Category category) {
        template.update(INSERT_CATEGORY, category.getCategory());
        int categoryId = template.queryForObject("select LAST_INSERT_ID()", Integer.class);
        category.setCategoryId(categoryId);
    }

    private static final String DELETE_BLOGSCATEGORIES
            = "delete from blogscategories where categoryId = ?";
    private static final String DELETE_CATEGORY
            = "delete from categories where categoryId = ?";
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void removeCategory(int categoryId) {
        template.update(DELETE_BLOGSCATEGORIES, categoryId);
        template.update(DELETE_CATEGORY, categoryId);
    }

    private static final String UPDATE_CATEGORY
            = "update categories set category = ? "
            + "where categoryId = ?";
    @Override
    public void updateCategory(Category category) {
        template.update(UPDATE_CATEGORY,
                category.getCategory(),
                category.getCategoryId());
    }

    private static final String SELECT_CATEGORY_BY_ID
            = "select * from categories where categoryId = ?";
    @Override
    public Category getCategoryById(int categoryId) {
        try {
            return template.queryForObject(SELECT_CATEGORY_BY_ID, new CategoryMapper(), categoryId);
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static final String SELECT_ALL_CATEGORIES
            = "select * from categories";
    @Override
    public List<Category> getAllCategories() {
        return template.query(SELECT_ALL_CATEGORIES, new CategoryMapper());
    }

    private static final String SELECT_CATEGORIES_BY_BLOG
            = "select c.categoryId, c.category from categories c "
            + "inner join blogscategories bc on bc.categoryId = c.categoryId "
            + "where bc.blogId = ?";
    @Override
    public List<Category> getCategoriesByBlog(Blog blog) {
        return template.query(SELECT_CATEGORIES_BY_BLOG, new CategoryMapper(), blog.getBlogId());
    }
    
}
