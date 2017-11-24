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
import com.adm.virtualrealitygladiators.dao.VRGTagDao;
import com.adm.virtualrealitygladiators.dao.VRGUserDao;
import com.adm.virtualrealitygladiators.dto.Blog;
import com.adm.virtualrealitygladiators.dto.Category;
import com.adm.virtualrealitygladiators.dto.Comment;
import com.adm.virtualrealitygladiators.dto.Image;
import com.adm.virtualrealitygladiators.dto.Tag;
import com.adm.virtualrealitygladiators.dto.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
public class VRGBlogController {

    private VRGBlogDao blogDao;
    private VRGCommentDao commentDao;
    private VRGCategoryDao categoryDao;
    private VRGTagDao tagDao;
    private VRGImageDao imageDao;
    private VRGUserDao userDao;

    @Inject
    public VRGBlogController(VRGBlogDao blogDao,
            VRGCommentDao commentDao, VRGCategoryDao categoryDao, VRGTagDao tagDao, VRGImageDao imageDao, VRGUserDao userDao) {
        this.blogDao = blogDao;
        this.commentDao = commentDao;
        this.categoryDao = categoryDao;
        this.tagDao = tagDao;
        this.imageDao = imageDao;
        this.userDao = userDao;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String displayLandingPage(Model model) {
        Blog mostRecent = blogDao.getMostRecentBlog();
        List<Blog> blogList = blogDao.getThreeRecentBlogs();
        List<Blog> otherBlogs = blogDao.getOtherRecentBlogs();
        model.addAttribute("mostRecent", mostRecent);
        model.addAttribute("blogList", blogList);
        model.addAttribute("otherBlogs", otherBlogs);
        model.addAttribute("categories", categoryDao.getAllCategories());
        return "index";
    }

    @RequestMapping(value = "/blogAdmin", method = RequestMethod.GET)
    public String displayCreateBlog(Model model) {
        model.addAttribute("categories", categoryDao.getAllCategories());
        return "createpostpage";
    }

    @RequestMapping(value = "/adminHome", method = RequestMethod.GET)
    public String displayAdminHome(Model model) {
        List<Blog> blogsPending = blogDao.getAllPendingBlogs();
        List<Comment> flaggedComments = commentDao.getAllFlaggedComments();

        model.addAttribute("blogsPending", blogsPending);
        model.addAttribute("flaggedComments", flaggedComments);
        model.addAttribute("categories", categoryDao.getAllCategories());

        return "adminhomepage";
    }

    @RequestMapping(value = "/postBlog", method = RequestMethod.POST)
    public String createBlog(HttpServletRequest request, Model model) {
        String blogEditor = request.getParameter("addBlogEditor");
        String title = request.getParameter("addBlogTitle");
        String authors = request.getParameter("addBlogAuthors");
        String[] catsParam = request.getParameterValues("addBlogCategories");
        String[] tagsParam = request.getParameterValues("addBlogTags");
        String imagePath = request.getParameter("addBlogImage");
        String imageDescription = request.getParameter("addBlogImageDesc");
        String summary = request.getParameter("addBlogSummary");
        String approvedParam = request.getParameter("addBlogApproved");
        List<String> errorMessages = new ArrayList<>();
        boolean hasErrors = false;
        Blog blog = new Blog();

        if (title.isEmpty() || title == null) {
            String errorMessage = "Please enter a title";
            errorMessages.add(errorMessage);
            hasErrors = true;
        }
        List<String> authorsList = new ArrayList<>();
        if (authors == null) {
            String errorMessage = "Please enter at least one author";
            errorMessages.add(errorMessage);
            hasErrors = true;
        } else {
            String[] tokens = authors.split(",");
            for (String token : tokens) {
                authorsList.add(token);
            }
        }
        List<Category> cats = new ArrayList<>();
        for (String cat : catsParam) {
            int catId = 0;
            try {
                catId = Integer.parseInt(cat);
            } catch (NumberFormatException e) {
                String errorMessage = "A category you have entered does not match those in the system. Please select from categories list";
                errorMessages.add(errorMessage);
                hasErrors = true;
            }
            cats.add(categoryDao.getCategoryById(catId));
        }
        List<Tag> tags = new ArrayList<>();
        for (String tag : tagsParam) {
            int tagId = 0;
            try {
                tagId = Integer.parseInt(tag);
            } catch (NumberFormatException e) {
                String errorMessage = "A tag you have entered does not match those in the system. Please select from tags list";
                errorMessages.add(errorMessage);
                hasErrors = true;
            }
            tags.add(tagDao.getTagById(tagId));
        }
        if (imagePath.isEmpty() || imagePath == null) {
            String errorMessage = "Please enter a path for an image";
            errorMessages.add(errorMessage);
            hasErrors = true;
        }
        if (imageDescription == null || imageDescription.isEmpty()) {
            String errorMessage = "Please enter a description for the image";
            errorMessages.add(errorMessage);
            hasErrors = true;
        }
        Image image = new Image();
        image.setPath(imagePath);
        image.setDescription(imageDescription);
        imageDao.addImage(image);
        if (summary.isEmpty() || summary == null) {
            String errorMessage = "Please enter a summary";
            errorMessages.add(errorMessage);
            hasErrors = true;
        }
        boolean approved = true;
        if (approvedParam.equals("false")) {
            approved = false;
        } else if (approvedParam.equals("true")) {
            approved = true;
        } else {
            String errorMessage = "Something has gone wrong with authoriztion. Please try again.";
            errorMessages.add(errorMessage);
            hasErrors = true;
        }
        if (blogEditor == null || blogEditor.isEmpty()) {
            String errorMessage = "Please enter the body of the post";
            errorMessages.add(errorMessage);
            hasErrors = true;
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = userDao.getUserByUserName(username);
        if (hasErrors) {
            model.addAttribute("errorMessages", errorMessages);
            model.addAttribute("categories", categoryDao.getAllCategories());
            model.addAttribute("tags", tagDao.getAllTags());
            return "createpostpage";
        }
        blog.setTitle(title);
        blog.setApproved(approved);
        blog.setAuthors(authorsList);
        blog.setDate(LocalDate.now());
        blog.setCategories(cats);
        blog.setHashtags(tags);
        blog.setImage(image);
        blog.setUser(user);
        List<Comment> comments = new ArrayList<>();
        blog.setComments(comments);
        blog.setPost(blogEditor);
        blog.setViewed(false);
        blog.setSummary(summary);
        blogDao.addBlog(blog);

        return "redirect: blog/" + blog.getBlogId();
    }

    @RequestMapping(value = "/blog/{blogId}", method = RequestMethod.GET)
    public String displayBlog(@PathVariable("blogId") int id, Model model) {
        Blog blog = blogDao.getBlogById(id);
        List<Comment> comments = commentDao.getUnflaggedCommentsByBlogId(id);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = userDao.getUserByUserName(username);
        model.addAttribute("user", user);
        model.addAttribute("blog", blog);
        model.addAttribute("comments", comments);
        model.addAttribute("categories", categoryDao.getAllCategories());
        return "displayBlog";
    }

//    @RequestMapping(value = "/deleteBlog/{blogId}")
//    public String deleteBlog(@PathVariable("blogId") int id, HttpServletRequest request, Model model) {
//        String editor = request.getParameter("editor");
//        blogDao.removeBlog(id);
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String username;
//        if (principal instanceof UserDetails) {
//            username = ((UserDetails) principal).getUsername();
//        } else {
//            username = principal.toString();
//        }
//        User user = userDao.getUserByUserName(username);
//        if (!(editor == null)) {
//            model.addAttribute("rejectedBlogs", blogDao.getAllRejectedBlogs(user.getUserId()));
//            model.addAttribute("pendingBlogs", blogDao.getAllPendingBlogsByUser(user.getUserId()));
//            return "editorDashboard";
//        } else {
//            model.addAttribute("allBlogs", blogDao.getAllBlogs());
//            return "adminAllBlogs";
//        }
//    }

    @RequestMapping(value = "/displayCreateBlog")
    public String displayCreateBlogPage(Model model) {
        List<Tag> allTags = tagDao.getAllTags();
        List<Category> categories = categoryDao.getAllCategories();

        model.addAttribute("tags", allTags);
        model.addAttribute("categories", categories);

        return "createpostpage";
    }

    @RequestMapping(value = "/displayBlogsByTag/{tagId}", method = RequestMethod.GET)
    public String displayBlogsByTag(@PathVariable("tagId") int id, Model model) {
        List<Blog> blogs = blogDao.getBlogsByTag(id);
        model.addAttribute("blogList", blogs);
        model.addAttribute("categories", categoryDao.getAllCategories());
        return "allBlogs";
    }

    @RequestMapping(value = "/adminAllBlogs", method = RequestMethod.GET)
    public String displayAdminAllBlogs(Model model) {
        List<Blog> blogs = blogDao.getAllBlogs();
        model.addAttribute("allBlogs", blogs);
        model.addAttribute("categories", categoryDao.getAllCategories());
        return "adminAllBlogs";
    }

    @RequestMapping(value = "/editorDashboard", method = RequestMethod.GET)
    public String displayEditorDashboard(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = userDao.getUserByUserName(username);

        List<Blog> rejectedBlogs = blogDao.getAllRejectedBlogs(user.getUserId());
        List<Blog> pendingBlogs = blogDao.getAllPendingBlogsByUser(user.getUserId());
        model.addAttribute("rejectedBlogs", rejectedBlogs);
        model.addAttribute("pendingBlogs", pendingBlogs);
        model.addAttribute("categories", categoryDao.getAllCategories());
        return "editorDashboard";
    }

    @RequestMapping(value = "/displayEditBlog/{blogId}", method = RequestMethod.GET)
    public String displayEditBlogForm(@PathVariable("blogId") int id, Model model) {
        Blog blog = blogDao.getBlogById(id);
        String authors = "";
        List<String> authorsList = blog.getAuthors();
        for (String author: authorsList) {
            authors += author;
            authors += ", ";
        }
        model.addAttribute("authors" , authors);
        model.addAttribute("blog", blog);
        model.addAttribute("categories", categoryDao.getAllCategories());
        model.addAttribute("tags", tagDao.getAllTags());
        return "editBlog";
    }

    @RequestMapping(value = "/editBlog", method = RequestMethod.POST)
    public String editBlog(HttpServletRequest request, Model model) {
        String blogIdParam = request.getParameter("editBlogId");
        String blogEditor = request.getParameter("editBlogEditor");
        String title = request.getParameter("editBlogTitle");
        String authors = request.getParameter("editBlogAuthors");
        String[] catsParam = request.getParameterValues("editBlogCategories");
        String[] tagsParam = request.getParameterValues("editBlogTags");
        String imagePath = request.getParameter("editBlogImage");
        String imageDescription = request.getParameter("editBlogImageDesc");
        String summary = request.getParameter("editBlogSummary");
        String approvedParam = request.getParameter("editBlogApproved");
        List<String> errorMessages = new ArrayList<>();
        boolean hasErrors = false;
        int blogId = 0;
        Blog blog = new Blog();
        try {
            blogId = Integer.parseInt(blogIdParam);
        } catch (NumberFormatException e) {
            String errorMessage = "Something went wrong. Contact your system administrator if problem persists";
            errorMessages.add(errorMessage);
            hasErrors = true;
        }
        if (title == null || title.isEmpty()) {
            String errorMessage = "Please enter a title";
            errorMessages.add(errorMessage);
            hasErrors = true;
        }
        List<String> authorsList = new ArrayList<>();
        if (authors == null) {
            String errorMessage = "Please enter at least one author";
            errorMessages.add(errorMessage);
            hasErrors = true;
        } else {
            String[] tokens = authors.split(",");
            for (String token : tokens) {
                token = token.trim();
                authorsList.add(token);
            }
        }
        List<Category> cats = new ArrayList<>();
        for (String cat : catsParam) {
            int catId = 0;
            try {
                catId = Integer.parseInt(cat);
            } catch (NumberFormatException e) {
                String errorMessage = "A category you have entered does not match those in the system. Please select from categories list";
                errorMessages.add(errorMessage);
                hasErrors = true;
            }
            cats.add(categoryDao.getCategoryById(catId));
        }
        List<Tag> tags = new ArrayList<>();
        for (String tag : tagsParam) {
            int tagId = 0;
            try {
                tagId = Integer.parseInt(tag);
            } catch (NumberFormatException e) {
                String errorMessage = "A tag you have entered does not match those in the system. Please select from tags list";
                errorMessages.add(errorMessage);
                hasErrors = true;
            }
            tags.add(tagDao.getTagById(tagId));
        }
        if (imagePath == null || imagePath.isEmpty()) {
            String errorMessage = "Please enter a path for an image";
            errorMessages.add(errorMessage);
            hasErrors = true;
        }
        if (imageDescription == null || imageDescription.isEmpty()) {
            String errorMessage = "Please enter a description for the image";
            errorMessages.add(errorMessage);
            hasErrors = true;
        }
        Image image = new Image();
        image.setPath(imagePath);
        image.setDescription(imageDescription);
        imageDao.addImage(image);
        if (summary == null || summary.isEmpty()) {
            String errorMessage = "Please enter a summary";
            errorMessages.add(errorMessage);
            hasErrors = true;
        }
        boolean approved = true;
        if (approvedParam.equals("false")) {
            approved = false;
        } else if (approvedParam.equals("true")) {
            approved = true;
        } else {
            String errorMessage = "Something has gone wrong with authoriztion. Please try again.";
            errorMessages.add(errorMessage);
            hasErrors = true;
        }
        if (blogEditor == null || blogEditor.isEmpty()) {
            String errorMessage = "Please enter the body of the post";
            errorMessages.add(errorMessage);
            hasErrors = true;
        }
        if (hasErrors) {
            model.addAttribute("errorMessages", errorMessages);
            model.addAttribute("categories", categoryDao.getAllCategories());
            model.addAttribute("tags", tagDao.getAllTags());
            return "createpostpage";
        }
        blog.setBlogId(blogId);
        blog.setTitle(title);
        blog.setApproved(approved);
        blog.setAuthors(authorsList);
        blog.setDate(LocalDate.now());
        blog.setCategories(cats);
        blog.setHashtags(tags);
        blog.setImage(image);
        List<Comment> comments = new ArrayList<>();
        blog.setComments(comments);
        blog.setPost(blogEditor);
        blog.setViewed(false);
        blog.setSummary(summary);
        blog.setUser(userDao.getUserByBlogId(blogId));
        blogDao.updateBlog(blog);
        return "redirect: blog/" + blog.getBlogId();
    }
    
    @RequestMapping(value = "/cancelEdit" , method = RequestMethod.POST)
    public String cancelEdit(HttpServletRequest request, Model model) {
        String isAdmin = request.getParameter("editBlogAdmin");
        if(!(isAdmin == null)) {
            return "redirect: adminHome/";
        } else {
            return "redirect: editorDashboard";
        }
    }

    @RequestMapping(value = "/approveBlog/{blogId}")
    public String approveBlog(@PathVariable("blogId") int id, Model model) {
        Blog blog = blogDao.getBlogById(id);
        blog.setViewed(true);
        blog.setApproved(true);
        blogDao.updateBlog(blog);
        List<Blog> blogsPending = blogDao.getAllPendingBlogs();
        List<Comment> flaggedComments = commentDao.getAllFlaggedComments();
        model.addAttribute("blogsPending", blogsPending);
        model.addAttribute("flaggedComments", flaggedComments);
        model.addAttribute("categories", categoryDao.getAllCategories());
        return "adminhomepage";
    }

    @RequestMapping(value = "/disapproveBlog/{blogId}")
    public String disapproveBlog(@PathVariable("blogId") int id, Model model) {
        Blog blog = blogDao.getBlogById(id);
        blog.setViewed(true);
        blog.setApproved(false);
        blogDao.updateBlog(blog);
        List<Blog> blogsPending = blogDao.getAllPendingBlogs();
        List<Comment> flaggedComments = commentDao.getAllFlaggedComments();
        model.addAttribute("blogsPending", blogsPending);
        model.addAttribute("flaggedComments", flaggedComments);
        model.addAttribute("categories", categoryDao.getAllCategories());
        return "adminhomepage";
    }

    @RequestMapping(value = "/addComment/{blogId}", method = RequestMethod.POST)
    public String addComment(@PathVariable("blogId") int id, HttpServletRequest request, Model model) {
        String author = request.getParameter("commentAuthor");
        String commentParam = request.getParameter("comment");
        String blogIdParam = request.getParameter("commentBlogId");
        List<String> errorMessages = new ArrayList<>();
        boolean hasErrors = false;
        int blogId = 0;
        try {
            blogId = Integer.parseInt(blogIdParam);
        } catch (NumberFormatException e) {
            String errorMessage = "Something went wrong. Contact your system administrator if problem persists";
            errorMessages.add(errorMessage);
            hasErrors = true;
        }
        if (author == null || author.isEmpty()) {
            String errorMessage = "Please input an author for the comment";
            errorMessages.add(errorMessage);
            hasErrors = true;
        }
        if (commentParam == null || commentParam.isEmpty()) {
            String errorMessage = "Please input a comment";
            errorMessages.add(errorMessage);
            hasErrors = true;
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = userDao.getUserByUserName(username);
        if (hasErrors) {
            model.addAttribute("errorMessages", errorMessages);
            model.addAttribute("user", user);
            model.addAttribute("comments", commentDao.getUnflaggedCommentsByBlogId(blogId));
            model.addAttribute("blog", blogDao.getBlogById(blogId));
            return "displayBlog";
        }
        Comment comment = new Comment();
        comment.setAuthor(author);
        comment.setComment(commentParam);
        comment.setDate(LocalDate.now());
        comment.setFlagged(false);
        commentDao.addComment(comment, blogId);
        model.addAttribute("blog", blogDao.getBlogById(blogId));
        model.addAttribute("user", user);
        model.addAttribute("comments", commentDao.getUnflaggedCommentsByBlogId(blogId));
        model.addAttribute("categories", categoryDao.getAllCategories());
        return "displayBlog";
    }

    @RequestMapping(value = "/approveComment/{commentId}")
    public String approveComment(@PathVariable("commentId") int id, Model model) {
        Comment comment = commentDao.getCommentById(id);
        comment.setFlagged(false);
        commentDao.updateComment(comment);
        model.addAttribute("blogsPending", blogDao.getAllPendingBlogs());
        model.addAttribute("flaggedComments", commentDao.getAllFlaggedComments());
        model.addAttribute("categories", categoryDao.getAllCategories());
        return "adminhomepage";
    }

    @RequestMapping(value = "/deleteComment/{commentId}" , method = {RequestMethod.POST , RequestMethod.GET})
    public String deleteComment(@PathVariable("commentId") int id, HttpServletRequest request, Model model) {
        String onBlog = request.getParameter("onBlog");
        Blog blog = blogDao.getBlogByCommentId(id);
        commentDao.removeComment(id);
        if (!(onBlog == null)) {
            model.addAttribute("blog", blog);
            model.addAttribute("comments", commentDao.getUnflaggedCommentsByBlogId(blog.getBlogId()));
            model.addAttribute("categories", categoryDao.getAllCategories());
            return "displayBlog";
        } else {
            model.addAttribute("blogsPending", blogDao.getAllPendingBlogs());
            model.addAttribute("flaggedComments", commentDao.getAllFlaggedComments());
            model.addAttribute("categories", categoryDao.getAllCategories());
            return "adminhomepage";
        }

    }

    @RequestMapping(value = "/flagComment/{commentId}/{blogId}")
    public String flagComment(@PathVariable("commentId") int id, @PathVariable("blogId") int blogId, HttpServletRequest request, Model model) {
        Comment comment = commentDao.getCommentById(id);
        comment.setFlagged(true);
        commentDao.updateComment(comment);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = userDao.getUserByUserName(username);
        model.addAttribute("blog", blogDao.getBlogById(blogId));
        model.addAttribute("comments", commentDao.getUnflaggedCommentsByBlogId(blogId));
        model.addAttribute("user", user);
        model.addAttribute("categories", categoryDao.getAllCategories());
        return "displayBlog";
    }

    @RequestMapping(value = "/displayAddCategoryPage", method = RequestMethod.GET)
    public String displayAddCategoryPage(Model model) {
        model.addAttribute("categories", categoryDao.getAllCategories());
        return "addCategory";
    }

    @RequestMapping(value = "/addCategory", method = RequestMethod.POST)
    public String addCategory(HttpServletRequest request, Model model) {
        String catNameParam = request.getParameter("addCatName");
        List<String> errorMessages = new ArrayList<>();
        if (catNameParam == null || catNameParam.isEmpty()) {
            String em = "Please enter a name for the new category";
            errorMessages.add(em);
            model.addAttribute("errorMessages", errorMessages);
            model.addAttribute("categories", categoryDao.getAllCategories());
            return "addCategory";
        }
        Category cat = new Category();
        cat.setCategory(catNameParam);
        categoryDao.addCategory(cat);
        model.addAttribute("categories", categoryDao.getAllCategories());
        model.addAttribute("tags", tagDao.getAllTags());
        model.addAttribute("categories", categoryDao.getAllCategories());
        return "createpostpage";
    }

    @RequestMapping(value = "/displayAddTagPage", method = RequestMethod.GET)
    public String displayAddTagPage(Model model) {
        model.addAttribute("categories", categoryDao.getAllCategories());
        return "addTag";
    }

    @RequestMapping(value = "/addTag", method = RequestMethod.POST)
    public String addTag(HttpServletRequest request, Model model) {
        String tagParam = request.getParameter("addTagName");
        List<String> errorMessages = new ArrayList<>();
        if (tagParam == null || tagParam.isEmpty()) {
            String em = "Please enter a hashtag";
            errorMessages.add(em);
            model.addAttribute("categories", categoryDao.getAllCategories());
            return "addTag";
        }
        Tag tag = new Tag();
        tag.setHashtag(tagParam);
        tagDao.addTag(tag);
        model.addAttribute("categories", categoryDao.getAllCategories());
        model.addAttribute("tags", tagDao.getAllTags());
        return "createpostpage";
    }

    @RequestMapping(value = "/allTagsCategories")
    public String displayAllTagsCategories(Model model) {
        model.addAttribute("tags", tagDao.getAllTags());
        model.addAttribute("categories", categoryDao.getAllCategories());
        return "tagsCategories";
    }

    @RequestMapping(value = "/displayEditCategory/{categoryId}", method = RequestMethod.GET)
    public String displayEditCategory(@PathVariable("categoryId") int id, Model model) {
        Category cat = categoryDao.getCategoryById(id);
        model.addAttribute("cat", cat);
        model.addAttribute("categories", categoryDao.getAllCategories());
        return "editCategory";
    }

    @RequestMapping(value = "/editCategory", method = RequestMethod.POST)
    public String editCategory(HttpServletRequest request, Model model) {
        String name = request.getParameter("editCatName");
        String idParam = request.getParameter("editCatId");
        List<String> errorMessages = new ArrayList<>();
        boolean hasErrors = false;
        if (name == null || name.isEmpty()) {
            String em = "Please enter a name for the category";
            errorMessages.add(em);
            hasErrors = true;
        }
        int catId = 0;
        try {
            catId = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            String em = "Something went wrong. Please contact your system administrator if problem persists";
            errorMessages.add(em);
            hasErrors = true;
        }
        Category cat = categoryDao.getCategoryById(catId);
        cat.setCategory(name);
        categoryDao.updateCategory(cat);
        model.addAttribute("tags", tagDao.getAllTags());
        model.addAttribute("categories", categoryDao.getAllCategories());
        return "tagsCategories";
    }

    @RequestMapping(value = "/displayEditTag/{tagId}")
    public String displayEditTag(@PathVariable("tagId") int id, Model model) {
        Tag tag = tagDao.getTagById(id);
        model.addAttribute("tag", tag);
        model.addAttribute("categories", categoryDao.getAllCategories());
        return "editTag";
    }

    @RequestMapping(value = "/editTag")
    public String editTag(HttpServletRequest request, Model model) {
        String idParam = request.getParameter("editTagId");
        String hashtag = request.getParameter("editTagName");
        List<String> errorMessages = new ArrayList<>();
        boolean hasErrors = false;
        if (hashtag == null || hashtag.isEmpty()) {
            String em = "Please enter a name for the hashtag";
            errorMessages.add(em);
            hasErrors = true;
        }
        int tagId = 0;
        try {
            tagId = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            String em = "Something went wrong. Please contact your system administrator if problem persists";
            errorMessages.add(em);
            hasErrors = true;
        }
        Tag tag = tagDao.getTagById(tagId);
        tag.setHashtag(hashtag);
        tagDao.updateTag(tag);
        model.addAttribute("categories", categoryDao.getAllCategories());
        model.addAttribute("tags", tagDao.getAllTags());
        return "tagsCategories";
    }

    @RequestMapping(value = "/deleteTag/{tagId}")
    public String deleteTag(@PathVariable("tagId") int id, Model model) {
        tagDao.removeTag(id);
        model.addAttribute("categories", categoryDao.getAllCategories());
        model.addAttribute("tags", tagDao.getAllTags());
        return "tagsCategories";
    }

    @RequestMapping(value = "/deleteCategory/{categoryId}")
    public String deleteCategory(@PathVariable("categoryId") int id, Model model) {
        categoryDao.removeCategory(id);
        model.addAttribute("categories", categoryDao.getAllCategories());
        model.addAttribute("tags", tagDao.getAllTags());
        return "tagsCategories";
    }
}
