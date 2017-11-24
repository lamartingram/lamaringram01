/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adm.virtualrealitygladiators.dao;

import com.adm.virtualrealitygladiators.dto.Tag;
import java.util.List;

/**
 *
 * @author adam-
 */
public interface VRGTagDao {
    
    public void addTag(Tag tag);
    
    public void removeTag(int tagId);
    
    public void updateTag(Tag tag);
    
    public Tag getTagById(int tagId);
    
    public List<Tag> getAllTags();
    
    public List<Tag> getTagsByBlogId(int blogId);
    
}
