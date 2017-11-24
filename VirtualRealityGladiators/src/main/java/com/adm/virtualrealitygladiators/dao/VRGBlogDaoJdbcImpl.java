/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adm.virtualrealitygladiators.dao;

import com.adm.virtualrealitygladiators.dto.Blog;
import com.adm.virtualrealitygladiators.dto.Category;
import com.adm.virtualrealitygladiators.dto.Comment;
import com.adm.virtualrealitygladiators.dto.Image;
import com.adm.virtualrealitygladiators.dto.Tag;
import com.adm.virtualrealitygladiators.dto.User;
import java.time.LocalDate;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author adam-
 */
public class VRGBlogDaoJdbcImpl implements VRGBlogDao {

    private JdbcTemplate template;

    public void setJdbcTemplate(JdbcTemplate template) {
        this.template = template;
    }

    private static final String INSERT_BLOGSCATEGORIES
            = "insert into blogscategories (categoryId, blogId) "
            + "values (?, ?)";

    private void insertBlogsCategories(Blog blog) {
        final int blogId = blog.getBlogId();
        final List<Category> cats = blog.getCategories();
        for (Category cat : cats) {
            template.update(INSERT_BLOGSCATEGORIES, cat.getCategoryId(), blogId);
        }
    }
    private static final String INSERT_BLOGSTAGS
            = "insert into blogstags (blogId, tagId) "
            + "values (?, ?)";

    private void insertBlogTags(Blog blog) {
        final int blogId = blog.getBlogId();
        final List<Tag> tags = blog.getHashtags();
        for (Tag tag : tags) {
            template.update(INSERT_BLOGSTAGS, blogId, tag.getTagId());
        }
    }
    private static final String INSERT_BLOGSAUTHORS
            = "insert into blogauthors (author, blogId) "
            + "values (?, ?)";

    private void insertBlogAuthors(Blog blog) {
        final int blogId = blog.getBlogId();
        final List<String> authors = blog.getAuthors();
        for (String author : authors) {
            template.update(INSERT_BLOGSAUTHORS, author, blogId);
        }
    }
    private static final String INSERT_BLOG
            = "insert into blogs (title, blogdate, post, approved, viewed, userId, summary, imageId) "
            + "values (?, ?, ?, ?, ?, ?, ?, ?)";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addBlog(Blog blog) {
        template.update(INSERT_BLOG,
                blog.getTitle(),
                blog.getDate().toString(),
                blog.getPost(),
                blog.isApproved(),
                blog.isViewed(),
                blog.getUser().getUserId(),
                blog.getSummary(),
                blog.getImage().getImageId());
        int blogId = template.queryForObject("select LAST_INSERT_ID()", Integer.class);
        blog.setBlogId(blogId);
        insertBlogTags(blog);
        insertBlogAuthors(blog);
        insertBlogsCategories(blog);
    }

    private static final String DELETE_BLOG
            = "delete from blogs where blogId = ?";
    private static final String DELETE_BLOGS_TAGS
            = "delete from blogstags where blogId = ?";
    private static final String DELETE_BLOGS_AUTHORS
            = "delete from blogauthors where blogId = ?";
    private static final String DELETE_COMMENTS_BY_BLOG
            = "delete from comments where blogId = ?";
    private static final String DELETE_BLOGS_CATEGORIES
            = "delete from blogscategories where blogId = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void removeBlog(int blogId) {
        template.update(DELETE_BLOGS_TAGS, blogId);
        template.update(DELETE_BLOGS_AUTHORS, blogId);
        template.update(DELETE_COMMENTS_BY_BLOG, blogId);
        template.update(DELETE_BLOGS_CATEGORIES, blogId);
        template.update(DELETE_BLOG, blogId);
    }

    private static final String UPDATE_BLOG
            = "update blogs set title = ?, blogDate = ?, post = ?, approved = ?, viewed = ?, userId = ?, summary = ?, imageId = ? "
            + "where blogId = ?";
    private static final String DELETE_BLOGAUTHORS
            = "delete from blogauthors where blogId = ?";
    private static final String DELETE_BLOGSTAGS
            = "delete from blogstags where blogId = ?";
    private static final String DELETE_BLOGSCATEGORIES
            = "delete from blogscategories where blogId = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateBlog(Blog blog) {
        template.update(DELETE_BLOGAUTHORS, blog.getBlogId());
        template.update(DELETE_BLOGSTAGS, blog.getBlogId());
        template.update(DELETE_BLOGSCATEGORIES, blog.getBlogId());
        template.update(UPDATE_BLOG,
                blog.getTitle(),
                blog.getDate().toString(),
                blog.getPost(),
                blog.isApproved(),
                blog.isViewed(),
                blog.getUser().getUserId(),
                blog.getSummary(),
                blog.getImage().getImageId(),
                blog.getBlogId());
        insertBlogAuthors(blog);
        insertBlogTags(blog);
        insertBlogsCategories(blog);
    }

    private static final String SELECT_AUTHORS_FOR_BLOG
            = "select author from blogauthors where blogId = ?";

    private Blog associateAuthorsWithBlog(Blog blog) {
        List<String> authors = template.queryForList(SELECT_AUTHORS_FOR_BLOG, String.class, blog.getBlogId());
        blog.setAuthors(authors);
        return blog;
    }

    private static final String SELECT_USER_FOR_BLOG
            = "select u.userId, u.userName, u.userPassword, u.enabled, u.authority "
            + "from users u inner join blogs b on u.userId = b.userId "
            + "where blogId = ?";

    private Blog associateUserWithBlog(Blog blog) {
        User user = new User();
        try {
            user = template.queryForObject(SELECT_USER_FOR_BLOG, new UserMapper(), blog.getBlogId());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        blog.setUser(user);
        return blog;
    }

    private static final String FIND_COMMENTS_FOR_BLOG
            = "select commentId, comment, flagged, author, commentDate "
            + "from comments where blogId = ?";

    private Blog findCommentsForBlog(Blog blog) {
        List<Comment> comments = template.query(FIND_COMMENTS_FOR_BLOG, new CommentMapper(), blog.getBlogId());
        blog.setComments(comments);
        return blog;
    }
    private static final String FIND_TAGS_FOR_BLOG
            = "select t.tagId, t.hashtag from tags t "
            + "inner join blogstags bt on t.tagId = bt.tagId "
            + "where blogId = ?";

    private Blog findTagsForBlog(Blog blog) {
        List<Tag> tags = template.query(FIND_TAGS_FOR_BLOG, new TagMapper(), blog.getBlogId());
        blog.setHashtags(tags);
        return blog;
    }
    private static final String FIND_IMAGE_FOR_BLOG
            = "select i.imageId, i.path, i.description from images i "
            + "inner join blogs b on b.imageId = i.imageId "
            + "where b.blogId = ?";

    private Blog findImageForBlog(Blog blog) {
        Image image = new Image();
        try {
            image = template.queryForObject(FIND_IMAGE_FOR_BLOG, new ImageMapper(), blog.getBlogId());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        blog.setImage(image);
        return blog;
    }

    private static final String FIND_CATEGORIES_FOR_BLOG
            = "select c.categoryId, c.category from categories c "
            + "inner join blogscategories bc on bc.categoryId = c.categoryId "
            + "where bc.blogId = ?";

    private Blog findCategoriesForBlog(Blog blog) {
        List<Category> cats = template.query(FIND_CATEGORIES_FOR_BLOG, new CategoryMapper(), blog.getBlogId());
        blog.setCategories(cats);
        return blog;
    }
    private List<Blog> associatePropertiesWithBlogs(List<Blog> blogs) {
        for (Blog blog : blogs) {
            findCommentsForBlog(blog);
            findTagsForBlog(blog);
            associateUserWithBlog(blog);
            associateAuthorsWithBlog(blog);
            findImageForBlog(blog);
            findCategoriesForBlog(blog);
        }
        return blogs;
    }
    private static final String SELECT_BLOG_BY_ID
            = "select * from blogs where blogId = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Blog getBlogById(int blogId) {
        Blog blog = new Blog();
        try {
            blog = template.queryForObject(SELECT_BLOG_BY_ID, new BlogMapper(), blogId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        findCommentsForBlog(blog);
        findTagsForBlog(blog);
        associateUserWithBlog(blog);
        associateAuthorsWithBlog(blog);
        findImageForBlog(blog);
        findCategoriesForBlog(blog);
        return blog;
    }

    private static final String SELECT_ALL_BLOGS
            = "select * from blogs order by blogdate desc";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Blog> getAllBlogs() {
        List<Blog> allBlogs = template.query(SELECT_ALL_BLOGS, new BlogMapper());
        return associatePropertiesWithBlogs(allBlogs);
    }

    private static final String SELECT_BLOGS_BY_DATE
            = "select * from blogs where blogDate = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Blog> getBlogsByDate(LocalDate date) {
        List<Blog> blogsByDate = template.query(SELECT_BLOGS_BY_DATE, new BlogMapper(), date.toString());
        return associatePropertiesWithBlogs(blogsByDate);
    }

    private static final String SELECT_BLOGS_BY_TAG
            = "select b.blogId, b.title, b.blogDate, b.post, b.approved, b.viewed, b.userId, b.summary, b.imageId from blogs b "
            + "inner join blogstags bt on bt.blogId = b.blogId "
            + "where tagId = ? AND approved = true";
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Blog> getBlogsByTag(int tagId) {
        List<Blog> blogs = template.query(SELECT_BLOGS_BY_TAG, new BlogMapper(), tagId);
        return associatePropertiesWithBlogs(blogs);
    }
    private static final String SELECT_MOST_RECENT_BLOG
            = "select * from blogs "
            + "where approved = true "
            + "order by blogdate desc "
            + "limit 0,1";
    public Blog getMostRecentBlog() {
        Blog blog = new Blog();
        try {
            blog = template.queryForObject(SELECT_MOST_RECENT_BLOG, new BlogMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        findCommentsForBlog(blog);
        findTagsForBlog(blog);
        associateUserWithBlog(blog);
        associateAuthorsWithBlog(blog);
        findImageForBlog(blog);
        findCategoriesForBlog(blog);
        return blog;
    }
    private static final String SELECT_RECENT_BLOGS
            = "select * from blogs "
            + "where approved = true "
            + "order by blogdate desc "
            + "limit 1,3";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Blog> getThreeRecentBlogs() {
        List<Blog> recentBlogs = template.query(SELECT_RECENT_BLOGS, new BlogMapper());
        return associatePropertiesWithBlogs(recentBlogs);
    }
    
    private static final String SELECT_OTHER_TEN
            = "select * from blogs "
            + "where approved = true "
            + "order by blogDate desc "
            + "limit 4,10";
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Blog> getOtherRecentBlogs() {
        List<Blog> blogs = template.query(SELECT_OTHER_TEN, new BlogMapper());
        return associatePropertiesWithBlogs(blogs);
    }

    private static final String SELECT_APPROVED_BLOGS
            = "select * from blogs "
            + "where approved = true";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Blog> getAllApprovedPosts() {
        List<Blog> blogs = template.query(SELECT_APPROVED_BLOGS, new BlogMapper());
        return associatePropertiesWithBlogs(blogs);
    }

    private static final String SELECT_UNAPPROVED_BLOGS
            = "select * from blogs where approved = false";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Blog> getAllUnapprovedPosts() {
        List<Blog> blogs = template.query(SELECT_UNAPPROVED_BLOGS, new BlogMapper());
        return associatePropertiesWithBlogs(blogs);
    }

    private static final String SELECT_BLOGS_BY_CATEGORY
            = "select b.blogId, b.title, b.blogDate, b.post, b.approved, b.viewed, b.userId, b.summary, b.imageId from blogs b "
            + "inner join blogscategories bc on b.blogId = bc.blogId "
            + "where bc.categoryId = ? AND b.approved = true";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Blog> getBlogsByCategory(Category category) {
        List<Blog> blogs = template.query(SELECT_BLOGS_BY_CATEGORY, new BlogMapper(), category.getCategoryId());
        return associatePropertiesWithBlogs(blogs);
    }

    private static final String SELECT_APPROVED_BLOGS_BY_USER
            = "select * from blogs "
            + "where approved = true AND userId = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Blog> getApprovedBlogsByUser(int userId) {
        List<Blog> blogs = template.query(SELECT_APPROVED_BLOGS_BY_USER, new BlogMapper(), userId);
        return associatePropertiesWithBlogs(blogs);
    }

    private static final String SELECT_ALL_PENDING_BY_USER
            = "select * from blogs where approved = false AND viewed = false AND userId = ?";
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Blog> getAllPendingBlogsByUser(int userId) {
        List<Blog> blogs = template.query(SELECT_ALL_PENDING_BY_USER, new BlogMapper(), userId);
        return associatePropertiesWithBlogs(blogs);
    }

    private static final String SELECT_ALL_REJECTED_BLOGS_BY_USER
            = "select * from blogs where approved = false AND viewed = true AND userId = ?";
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Blog> getAllRejectedBlogs(int userId) {
        List<Blog> blogs = template.query(SELECT_ALL_REJECTED_BLOGS_BY_USER, new BlogMapper(), userId);
        return associatePropertiesWithBlogs(blogs);
    }
    
    private static final String SELECT_ALL_PENDING_BLOGS
            = "select * from blogs where approved = false AND viewed = false";
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Blog> getAllPendingBlogs() {
        List<Blog> blogs = template.query(SELECT_ALL_PENDING_BLOGS, new BlogMapper());
        return associatePropertiesWithBlogs(blogs);
    }

    private static final String SELECT_BLOG_BY_COMMENT
            = "select blogId from comments where commentId = ?";
    @Override
    public Blog getBlogByCommentId(int commentId) {
        int blogId = 0;
        try {
            blogId = template.queryForObject(SELECT_BLOG_BY_COMMENT, Integer.class, commentId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return getBlogById(blogId);
    }

}
