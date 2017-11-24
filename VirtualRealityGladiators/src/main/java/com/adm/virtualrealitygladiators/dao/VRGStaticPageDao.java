/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adm.virtualrealitygladiators.dao;

import com.adm.virtualrealitygladiators.dto.StaticPage;
import com.adm.virtualrealitygladiators.dto.User;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author adam-
 */
public interface VRGStaticPageDao {
    
    public void addStaticPage(StaticPage page);
    
    public void removeStaticPage(int pageId);
    
    public void updateStaticPage(StaticPage page);
    
    public StaticPage getStaticPageById(int pageId);
    
    public List<StaticPage> getAllStaticPages();
    
    public List<StaticPage> getStaticPagesByDate(LocalDate date);
    
    public List<StaticPage> getStaticPagesByUser(User user);
    
    public StaticPage getStaticPageByName(String name);
    
}
