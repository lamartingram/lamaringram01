/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adm.virtualrealitygladiators;

import com.adm.virtualrealitygladiators.dao.VRGBlogDao;
import com.adm.virtualrealitygladiators.dao.VRGCategoryDao;
import com.adm.virtualrealitygladiators.dao.VRGCommentDao;
import com.adm.virtualrealitygladiators.dao.VRGImageDao;
import com.adm.virtualrealitygladiators.dao.VRGStaticPageDao;
import com.adm.virtualrealitygladiators.dao.VRGTagDao;
import com.adm.virtualrealitygladiators.dao.VRGUserDao;
import com.adm.virtualrealitygladiators.dto.Blog;
import com.adm.virtualrealitygladiators.dto.Category;
import com.adm.virtualrealitygladiators.dto.Tag;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Daniel McBride
 */
@Controller
public class VRGSearchController {

    private VRGBlogDao blogDao;
    private VRGCategoryDao categoryDao;
    private VRGCommentDao commentDao;
    private VRGImageDao imageDao;
    private VRGStaticPageDao staticPageDao;
    private VRGTagDao tagDao;
    private VRGUserDao userDao;

    @Inject
    public VRGSearchController(VRGBlogDao blogDao,
            VRGCategoryDao categoryDao, VRGCommentDao commentDao,
            VRGImageDao imageDao, VRGStaticPageDao spDao,
            VRGTagDao tagDao, VRGUserDao userDao) {
        this.blogDao = blogDao;
        this.categoryDao = categoryDao;
        this.commentDao = commentDao;
        this.imageDao = imageDao;
        this.staticPageDao = staticPageDao;
        this.tagDao = tagDao;
        this.userDao = userDao;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String displaySearchResults(HttpServletRequest request, Model model) {
        List<Blog> allBlogs = blogDao.getAllApprovedPosts();
        
        String searchTerm = request.getParameter("srch-term").toLowerCase();
        
        List<Blog> searchResults = new ArrayList();
        boolean notFound;
        
        for(Blog blog : allBlogs){
            notFound = true;
            
            if(blog.getTitle().toLowerCase().contains(searchTerm)){
                searchResults.add(blog);
                notFound = false;
            }
            
            if(notFound){
                for(Tag tag : blog.getHashtags()) {
                    if(tag.getHashtag().toLowerCase().contains(searchTerm)){
                        searchResults.add(blog);
                        notFound = false;
                        break;
                    }
                }
            }
            
            if(notFound){
                for(Category category : blog.getCategories()) {
                    if(category.getCategory().toLowerCase().contains(searchTerm)){
                        searchResults.add(blog);
                        notFound = false;
                        break;
                    }
                }
            }
            
        }
        
        model.addAttribute("blogList", searchResults);
        model.addAttribute("categories", categoryDao.getAllCategories());

        return "displaySearchResults";
    }
    
    @RequestMapping(value = "/category/{searchTerm}", method = RequestMethod.GET)
    public String displayBlogsByCategory(
            @PathVariable("searchTerm") String searchTerm, Model model){
        
        List<Blog> allBlogs = blogDao.getAllApprovedPosts();
        
        List<Blog> searchResults = new ArrayList();
        
        for(Blog blog : allBlogs){
            for(Category category : blog.getCategories()){
                if(category.getCategory().toLowerCase().contains(searchTerm.toLowerCase())){
                    searchResults.add(blog);
                    break;
                }
            }
        }
        
        model.addAttribute("blogList", searchResults);
        model.addAttribute("categories", categoryDao.getAllCategories());
        model.addAttribute("searchTerm", searchTerm);
        
        return "displayBlogsInCategory";
    }
}
