/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adm.virtualrealitygladiators.dao;

import com.adm.virtualrealitygladiators.dto.Blog;
import com.adm.virtualrealitygladiators.dto.Category;
import java.util.List;

/**
 *
 * @author adam-
 */
public interface VRGCategoryDao {
    
    public void addCategory(Category category);
    
    public void removeCategory(int categoryId);
    
    public void updateCategory(Category category);
    
    public Category getCategoryById(int categoryId);
    
    public List<Category> getAllCategories();
    
    public List<Category> getCategoriesByBlog(Blog blog);
    
}
