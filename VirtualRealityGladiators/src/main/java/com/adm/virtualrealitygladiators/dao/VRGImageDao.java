/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adm.virtualrealitygladiators.dao;

import com.adm.virtualrealitygladiators.dto.Image;
import java.util.List;

/**
 *
 * @author adam-
 */
public interface VRGImageDao {
    
    public void addImage(Image image);
    
    public void removeImage(int imageId, int blogId);
    
    public void updateImage(Image image);
    
    public Image getImageById(int imageId);
    
    public List<Image> getAllImages();
    
}
