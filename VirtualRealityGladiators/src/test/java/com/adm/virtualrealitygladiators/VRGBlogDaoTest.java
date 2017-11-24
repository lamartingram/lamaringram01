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
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author adam-
 */
public class VRGBlogDaoTest {

    private VRGCommentDao commentDao;
    private VRGTagDao tagDao;
    private VRGUserDao userDao;
    private VRGBlogDao blogDao;
    private VRGImageDao imageDao;
    private VRGCategoryDao categoryDao;
    private JdbcTemplate template;

    private Blog[] blogArray;
    private Tag[] tagArray;
    private Comment[] commentArray;
    private User[] userArray;
    private Image[] imageArray;
    private Category[] categoryArray;

    public void setJdbcTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public VRGBlogDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        commentDao = ctx.getBean("VRGCommentDao", VRGCommentDao.class);
        tagDao = ctx.getBean("VRGTagDao", VRGTagDao.class);
        userDao = ctx.getBean("VRGUserDao", VRGUserDao.class);
        blogDao = ctx.getBean("VRGBlogDao", VRGBlogDao.class);
        imageDao = ctx.getBean("VRGImageDao", VRGImageDao.class);
        categoryDao = ctx.getBean("VRGCategoryDao", VRGCategoryDao.class);
        template = ctx.getBean("jdbcTemplate", JdbcTemplate.class);

        template.execute("delete from comments where 1 = 1");
        template.execute("delete from blogauthors where 1 = 1");
        template.execute("delete from blogstags where 1 = 1");
        template.execute("delete from blogscategories where 1 = 1");
        template.execute("delete from categories where 1 = 1");
        template.execute("delete from blogs where 1 = 1");
        template.execute("delete from images where 1 = 1");
        template.execute("delete from staticpages where 1 = 1");
        template.execute("delete from users where 1 = 1");
        template.execute("delete from tags where 1 = 1");

        Comment comment = new Comment();
        comment.setComment("Comment One");
        comment.setAuthor("Comment Author One");
        comment.setDate(LocalDate.parse("2009-09-09"));
        comment.setFlagged(false);
        Comment comment2 = new Comment();
        comment2.setComment("Comment Two");
        comment2.setAuthor("Comment Author Two");
        comment2.setDate(LocalDate.parse("2017-01-01"));
        comment2.setFlagged(false);
        commentArray = new Comment[]{
            comment, comment2,};

        User user = new User();
        user.setUserName("userName1");
        user.setPassword("password1");
        user.setEnabled(true);
        user.setAuthority("ROLE_ADMIN");
        User user2 = new User();
        user2.setUserName("userName2");
        user2.setPassword("password2");
        user2.setEnabled(true);
        user2.setAuthority("ROLE_MODERATOR");
        userArray = new User[]{
            user, user2
        };

        Tag tag = new Tag();
        tag.setHashtag("#gladiator");
        Tag tag2 = new Tag();
        tag2.setHashtag("#pong");
        tagArray = new Tag[]{
            tag, tag2
        };

        Image image = new Image();
        image.setPath("Image Path One");
        image.setDescription("Image Description One");

        Image image2 = new Image();
        image2.setPath("Image Path Two");
        image2.setDescription("Image Description Two");
        imageArray = new Image[]{
            image, image2
        };

        Category category = new Category();
        category.setCategory("Category One");
        Category category2 = new Category();
        category2.setCategory("Category Two");

        categoryArray = new Category[]{
            category, category2
        };

        Blog blog = new Blog();
        blog.setTitle("Title One");
        blog.setSummary("Summary One");
        List<String> authors1 = new ArrayList<>();
        authors1.add("Jay Ajayi");
        authors1.add("Carson Wentz");
        blog.setAuthors(authors1);
        blog.setApproved(true);
        List<Comment> comments1 = new ArrayList<>();
        comments1.add(comment);
        blog.setComments(comments1);
        blog.setUser(user);
        blog.setDate(LocalDate.parse("2009-09-09"));
        List<Tag> tags1 = new ArrayList<>();
        tags1.add(tag);
        tags1.add(tag2);
        blog.setHashtags(tags1);
        blog.setPost("This post will usually be much longer than this.");
        blog.setImage(image);
        blog.setViewed(true);

        Blog blog2 = new Blog();
        blog2.setTitle("Title Two");
        blog2.setSummary("Summary Two");
        blog2.setDate(LocalDate.parse("2017-01-01"));
        blog2.setPost("They will also be much longer than this");
        blog2.setApproved(true);
        List<String> authors2 = new ArrayList<>();
        authors2.add("Carson Wentz");
        authors2.add("Lebron James");
        authors2.add("Willy Wonka");
        blog2.setAuthors(authors2);
        List<Comment> comments2 = new ArrayList<>();
        comments2.add(comment);
        comments2.add(comment2);
        blog2.setComments(comments2);
        List<Tag> tags2 = new ArrayList<>();
        tags2.add(tag);
        blog2.setHashtags(tags2);
        blog2.setUser(user2);
        blog2.setImage(image2);
        blog2.setViewed(true);

        Blog blog3 = new Blog();
        blog3.setTitle("Title 3");
        blog3.setImage(image);
        blog3.setApproved(true);
        blog3.setAuthors(authors1);
        blog3.setDate(LocalDate.parse("2017-08-08"));
        blog3.setHashtags(tags2);
        blog3.setPost("blah blah blah");
        blog3.setUser(user);
        blog3.setSummary("Summary");
        blog3.setViewed(true);

        Blog blog4 = new Blog();
        blog4.setTitle("Title 4");
        blog4.setImage(image);
        blog4.setApproved(true);
        blog4.setAuthors(authors1);
        blog4.setDate(LocalDate.parse("1990-08-08"));
        blog4.setHashtags(tags2);
        blog4.setPost("blah blah blah blah");
        blog4.setUser(user);
        blog4.setSummary("Summary 4");
        blog4.setViewed(true);

        blogArray = new Blog[]{
            blog, blog2, blog3, blog4
        };

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddGetBlog() {
        User user = userArray[0];
        userDao.addUser(user);
        Image image = imageArray[0];
        imageDao.addImage(image);
        Blog blog = blogArray[0];
        Comment comment = commentArray[0];
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        blog.setComments(comments);
        blog.setUser(user);
        Tag tag = tagArray[0];
        tagDao.addTag(tag);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        blog.setHashtags(tags);
        blog.setImage(image);
        Category cat = categoryArray[0];
        List<Category> cats = new ArrayList<>();
        cats.add(cat);
        blog.setCategories(cats);
        categoryDao.addCategory(cat);
        blogDao.addBlog(blog);
        commentDao.addComment(comment, blog.getBlogId());
        Blog fromDao = blogDao.getBlogById(blog.getBlogId());
        assertEquals(blog, fromDao);
    }

    @Test
    public void testRemoveBlog() {
        Image image = imageArray[0];
        imageDao.addImage(image);
        User user = userArray[0];
        userDao.addUser(user);
        Blog blog = blogArray[0];
        Comment comment = commentArray[0];
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        blog.setComments(comments);
        blog.setUser(user);
        Tag tag = tagArray[0];
        tagDao.addTag(tag);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        blog.setHashtags(tags);
        blog.setImage(image);
        Category cat = categoryArray[0];
        List<Category> cats = new ArrayList<>();
        cats.add(cat);
        blog.setCategories(cats);
        categoryDao.addCategory(cat);
        blogDao.addBlog(blog);
        commentDao.addComment(comment, blog.getBlogId());
        Blog fromDao = blogDao.getBlogById(blog.getBlogId());
        assertEquals(blog, fromDao);
        blogDao.removeBlog(blog.getBlogId());
        assertNull(blogDao.getBlogById(blog.getBlogId()));
    }

    @Test
    public void testUpdateBlog() {
        Image image = imageArray[0];
        imageDao.addImage(image);
        User user = userArray[0];
        userDao.addUser(user);
        Blog blog = blogArray[0];
        Comment comment = commentArray[0];
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        blog.setComments(comments);
        blog.setUser(user);
        Tag tag = tagArray[0];
        tagDao.addTag(tag);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        blog.setHashtags(tags);
        blog.setImage(image);
        Category cat = categoryArray[0];
        List<Category> cats = new ArrayList<>();
        cats.add(cat);
        blog.setCategories(cats);
        categoryDao.addCategory(cat);
        blogDao.addBlog(blog);
        commentDao.addComment(comment, blog.getBlogId());
        Blog fromDao = blogDao.getBlogById(blog.getBlogId());
        assertEquals(blog, fromDao);
        Blog blog2 = blogArray[1];
        blog2.setBlogId(blog.getBlogId());
        blog2.setHashtags(tags);
        blog2.setUser(user);
        blog2.setComments(comments);
        blog2.setImage(image);
        Category cat2 = categoryArray[1];
        List<Category> cats2 = new ArrayList<>();
        cats2.add(cat2);
        blog2.setCategories(cats2);
        categoryDao.addCategory(cat2);
        blogDao.updateBlog(blog2);
        fromDao = blogDao.getBlogById(blog2.getBlogId());
        assertEquals(blog2, fromDao);
    }

    @Test
    public void testGetAllBlogs() {
        Image image = imageArray[0];
        imageDao.addImage(image);
        User user = userArray[0];
        userDao.addUser(user);
        Blog blog = blogArray[0];
        Comment comment = commentArray[0];
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        blog.setComments(comments);
        blog.setUser(user);
        Tag tag = tagArray[0];
        tagDao.addTag(tag);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        blog.setHashtags(tags);
        blog.setImage(image);
        Category cat = categoryArray[0];
        List<Category> cats = new ArrayList<>();
        cats.add(cat);
        blog.setCategories(cats);
        categoryDao.addCategory(cat);
        blogDao.addBlog(blog);
        commentDao.addComment(comment, blog.getBlogId());
        Blog fromDao = blogDao.getBlogById(blog.getBlogId());
        assertEquals(blog, fromDao);
        assertEquals(1, blogDao.getAllBlogs().size());
        assertTrue(blogDao.getAllBlogs().contains(blog));

        Blog blog2 = blogArray[1];
        blog2.setHashtags(tags);
        blog2.setUser(user);
        Comment comment2 = commentArray[1];
        List<Comment> comments2 = new ArrayList<>();
        comments2.add(comment2);
        blog2.setComments(comments2);
        blog2.setImage(image);
        Category cat2 = categoryArray[1];
        List<Category> cats2 = new ArrayList<>();
        cats2.add(cat2);
        blog2.setCategories(cats2);
        categoryDao.addCategory(cat2);
        blogDao.addBlog(blog2);
        commentDao.addComment(comment2, blog2.getBlogId());
        fromDao = blogDao.getBlogById(blog2.getBlogId());
        assertEquals(blog2, fromDao);

        List<Blog> blogs = blogDao.getAllBlogs();
        assertEquals(2, blogDao.getAllBlogs().size());
        assertTrue(blogDao.getAllBlogs().contains(blog));
        assertTrue(blogDao.getAllBlogs().contains(blog2));
    }

    @Test
    public void testGetBlogsByDate() {
        Image image = imageArray[0];
        imageDao.addImage(image);
        User user = userArray[0];
        userDao.addUser(user);
        Blog blog = blogArray[0];
        blog.setUser(user);
        Tag tag = tagArray[0];
        tagDao.addTag(tag);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        blog.setHashtags(tags);
        Comment comment = commentArray[0];
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        blog.setComments(comments);
        blog.setImage(image);
        Category cat = categoryArray[0];
        List<Category> cats = new ArrayList<>();
        cats.add(cat);
        blog.setCategories(cats);
        categoryDao.addCategory(cat);
        blogDao.addBlog(blog);
        commentDao.addComment(comment, blog.getBlogId());
        Blog blog2 = blogArray[1];
        blog2.setUser(user);
        blog2.setHashtags(tags);
        Comment com2 = commentArray[1];
        List<Comment> c2 = new ArrayList<>();
        c2.add(com2);
        blog2.setComments(c2);
        blog2.setImage(image);
        Category cat2 = categoryArray[1];
        List<Category> cats2 = new ArrayList<>();
        cats2.add(cat2);
        blog2.setCategories(cats2);
        categoryDao.addCategory(cat2);
        blogDao.addBlog(blog2);
        commentDao.addComment(com2, blog2.getBlogId());
        LocalDate date = LocalDate.parse("2009-09-09");
        List<Blog> blogsDate1 = blogDao.getBlogsByDate(date);
        assertEquals(1, blogsDate1.size());
        Blog blogToCompare = blogsDate1.get(0);
        assertEquals(blogToCompare, blog);
        date = LocalDate.parse("2017-01-01");
        assertEquals(1, blogDao.getBlogsByDate(date).size());
        assertTrue(blogDao.getBlogsByDate(date).contains(blog2));
    }

    @Test
    public void testGetBlogsByTag() {
        Image image = imageArray[0];
        imageDao.addImage(image);
        User user = userArray[0];
        userDao.addUser(user);
        Blog blog = blogArray[0];
        blog.setUser(user);
        Tag tag = tagArray[0];
        tagDao.addTag(tag);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        blog.setHashtags(tags);
        Comment comment = commentArray[0];
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        blog.setComments(comments);
        blog.setImage(image);
        Category cat = categoryArray[0];
        List<Category> cats = new ArrayList<>();
        cats.add(cat);
        blog.setCategories(cats);
        categoryDao.addCategory(cat);
        blogDao.addBlog(blog);
        commentDao.addComment(comment, blog.getBlogId());
        Blog blog2 = blogArray[1];
        Tag tag2 = tagArray[1];
        tagDao.addTag(tag2);
        List<Tag> tags2 = new ArrayList<>();
        tags2.add(tag2);
        blog2.setHashtags(tags2);
        blog2.setUser(user);
        Comment com2 = commentArray[1];
        List<Comment> comm2 = new ArrayList<>();
        comm2.add(com2);
        blog2.setComments(comm2);
        blog2.setImage(image);
        Category cat2 = categoryArray[1];
        List<Category> cats2 = new ArrayList<>();
        cats2.add(cat2);
        blog2.setCategories(cats2);
        categoryDao.addCategory(cat2);
        blogDao.addBlog(blog2);
        commentDao.addComment(com2, blog2.getBlogId());
        List<Blog> blogsByTag1 = blogDao.getBlogsByTag(tag.getTagId());
        assertEquals(1, blogsByTag1.size());
        Blog blogToCheck = blogsByTag1.get(0);
        assertEquals(blog, blogToCheck);
        assertTrue(blogsByTag1.contains(blog));
        List<Blog> blogsByTag2 = blogDao.getBlogsByTag(tag2.getTagId());
        assertEquals(1, blogsByTag2.size());
        assertTrue(blogsByTag2.contains(blog2));
    }
    
    @Test
    public void testGetMostRecentBlog() {
        Image image = imageArray[0];
        imageDao.addImage(image);
        User user = userArray[0];
        userDao.addUser(user);
        Blog blog = blogArray[0];
        blog.setUser(user);
        Tag tag = tagArray[0];
        tagDao.addTag(tag);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        blog.setHashtags(tags);
        Comment comment = commentArray[0];
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        blog.setComments(comments);
        blog.setImage(image);
        Category cat = categoryArray[0];
        List<Category> cats = new ArrayList<>();
        cats.add(cat);
        blog.setCategories(cats);
        blog.setDate(LocalDate.parse("1990-01-01"));
        categoryDao.addCategory(cat);
        blogDao.addBlog(blog);
        commentDao.addComment(comment, blog.getBlogId());
        Blog blog2 = blogArray[1];
        Tag tag2 = tagArray[1];
        tagDao.addTag(tag2);
        List<Tag> tags2 = new ArrayList<>();
        tags2.add(tag2);
        blog2.setHashtags(tags2);
        blog2.setUser(user);
        Comment com2 = commentArray[1];
        List<Comment> comm2 = new ArrayList<>();
        comm2.add(com2);
        blog2.setComments(comm2);
        blog2.setImage(image);
        Category cat2 = categoryArray[1];
        List<Category> cats2 = new ArrayList<>();
        cats2.add(cat2);
        blog2.setCategories(cats2);
        blog2.setDate(LocalDate.parse("2017-10-10"));
        categoryDao.addCategory(cat2);
        blogDao.addBlog(blog2);
        commentDao.addComment(com2, blog2.getBlogId());
        Blog fromDao = blogDao.getMostRecentBlog();
        assertEquals(blog2, fromDao);
        assertNotEquals(blog, fromDao);
    }

    @Test
    public void testGetRecentBlogs() {
        Image image = imageArray[0];
        imageDao.addImage(image);
        User user = userArray[0];
        userDao.addUser(user);
        Blog blog = blogArray[0];
        blog.setUser(user);
        Tag tag = tagArray[0];
        tagDao.addTag(tag);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        blog.setHashtags(tags);
        Comment comment = commentArray[0];
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        blog.setComments(comments);
        blog.setImage(image);
        Category cat = categoryArray[0];
        List<Category> cats = new ArrayList<>();
        cats.add(cat);
        blog.setCategories(cats);
        blog.setDate(LocalDate.parse("2000-10-10"));
        categoryDao.addCategory(cat);
        blogDao.addBlog(blog);
        commentDao.addComment(comment, blog.getBlogId());
        Blog blog2 = blogArray[1];
        Tag tag2 = tagArray[1];
        tagDao.addTag(tag2);
        List<Tag> tags2 = new ArrayList<>();
        tags2.add(tag2);
        blog2.setHashtags(tags2);
        blog2.setUser(user);
        Comment com2 = commentArray[1];
        List<Comment> comm2 = new ArrayList<>();
        comm2.add(com2);
        blog2.setComments(comm2);
        blog2.setImage(image);
        Category cat2 = categoryArray[1];
        List<Category> cats2 = new ArrayList<>();
        cats2.add(cat2);
        blog2.setCategories(cats2);
        blog2.setDate(LocalDate.parse("2017-05-05"));
        categoryDao.addCategory(cat2);
        blogDao.addBlog(blog2);
        commentDao.addComment(com2, blog2.getBlogId());
        Comment comment3 = new Comment();
        comment3.setAuthor("Comment Author 3");
        comment3.setComment("Comment 3");
        comment3.setDate(LocalDate.parse("2017-10-10"));
        comment3.setFlagged(false);
        List<Comment> comments3 = new ArrayList<>();
        comments3.add(comment3);
        Blog blog3 = blogArray[2];
        blog3.setComments(comments3);
        Category cat3 = new Category();
        cat3.setCategory("Category 3");
        List<Category> cats3 = new ArrayList<>();
        cats3.add(cat3);
        blog3.setCategories(cats3);
        blog3.setDate(LocalDate.parse("2017-09-09"));
        categoryDao.addCategory(cat3);
        blogDao.addBlog(blog3);
        commentDao.addComment(comment3, blog3.getBlogId());
        Comment comment4 = new Comment();
        comment4.setAuthor("Comment Author 4");
        comment4.setComment("Comment 4");
        comment4.setDate(LocalDate.parse("2017-10-10"));
        comment4.setFlagged(false);
        List<Comment> comments4 = new ArrayList<>();
        comments4.add(comment4);
        Blog blog4 = blogArray[3];
        blog4.setComments(comments4);
        Category cat4 = new Category();
        cat4.setCategory("Category 4");
        List<Category> cats4 = new ArrayList<>();
        cats4.add(cat4);
        blog4.setCategories(cats4);
        blog4.setDate(LocalDate.parse("2017-08-08"));
        categoryDao.addCategory(cat4);
        blogDao.addBlog(blog4);
        commentDao.addComment(comment4, blog4.getBlogId());
        List<Blog> blogs = blogDao.getThreeRecentBlogs();
        assertEquals(3, blogDao.getThreeRecentBlogs().size());
        assertTrue(blogDao.getThreeRecentBlogs().contains(blog4));
        assertTrue(blogDao.getThreeRecentBlogs().contains(blog2));
        assertTrue(blogDao.getThreeRecentBlogs().contains(blog));
        assertFalse(blogDao.getThreeRecentBlogs().contains(blog3));
    }

    @Test
    public void testGetAllApprovedBlogs() {
        Image image = imageArray[0];
        imageDao.addImage(image);
        User user = userArray[0];
        userDao.addUser(user);
        Blog blog = blogArray[0];
        blog.setUser(user);
        Tag tag = tagArray[0];
        tagDao.addTag(tag);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        blog.setHashtags(tags);
        Comment comment = commentArray[0];
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        blog.setComments(comments);
        blog.setImage(image);
        Category cat = categoryArray[0];
        List<Category> cats = new ArrayList<>();
        cats.add(cat);
        blog.setCategories(cats);
        categoryDao.addCategory(cat);
        blogDao.addBlog(blog);
        commentDao.addComment(comment, blog.getBlogId());
        Blog blog2 = blogArray[1];
        Tag tag2 = tagArray[1];
        tagDao.addTag(tag2);
        List<Tag> tags2 = new ArrayList<>();
        tags2.add(tag2);
        blog2.setHashtags(tags2);
        blog2.setUser(user);
        Comment com2 = commentArray[1];
        List<Comment> comm2 = new ArrayList<>();
        comm2.add(com2);
        blog2.setComments(comm2);
        blog2.setImage(image);
        blog2.setApproved(false);
        Category cat2 = categoryArray[1];
        List<Category> cats2 = new ArrayList<>();
        cats2.add(cat2);
        blog2.setCategories(cats2);
        categoryDao.addCategory(cat2);
        blogDao.addBlog(blog2);
        commentDao.addComment(com2, blog2.getBlogId());
        assertEquals(1, blogDao.getAllApprovedPosts().size());
        assertTrue(blogDao.getAllApprovedPosts().contains(blog));
        assertFalse(blogDao.getAllApprovedPosts().contains(blog2));
    }

    @Test
    public void testGetAllUnapprovedBlogs() {
        Image image = imageArray[0];
        imageDao.addImage(image);
        User user = userArray[0];
        userDao.addUser(user);
        Blog blog = blogArray[0];
        blog.setUser(user);
        Tag tag = tagArray[0];
        tagDao.addTag(tag);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        blog.setHashtags(tags);
        Comment comment = commentArray[0];
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        blog.setComments(comments);
        blog.setImage(image);
        Category cat = categoryArray[0];
        List<Category> cats = new ArrayList<>();
        cats.add(cat);
        blog.setCategories(cats);
        categoryDao.addCategory(cat);
        blogDao.addBlog(blog);
        commentDao.addComment(comment, blog.getBlogId());
        Blog blog2 = blogArray[1];
        Tag tag2 = tagArray[1];
        tagDao.addTag(tag2);
        List<Tag> tags2 = new ArrayList<>();
        tags2.add(tag2);
        blog2.setHashtags(tags2);
        blog2.setUser(user);
        Comment com2 = commentArray[1];
        List<Comment> comm2 = new ArrayList<>();
        comm2.add(com2);
        blog2.setComments(comm2);
        blog2.setImage(image);
        blog2.setApproved(false);
        Category cat2 = categoryArray[1];
        List<Category> cats2 = new ArrayList<>();
        cats2.add(cat2);
        blog2.setCategories(cats2);
        categoryDao.addCategory(cat2);
        
        blogDao.addBlog(blog2);
        commentDao.addComment(com2, blog2.getBlogId());
        List<Blog> unapproved = blogDao.getAllUnapprovedPosts();
        assertEquals(1, blogDao.getAllUnapprovedPosts().size());
        assertFalse(blogDao.getAllUnapprovedPosts().contains(blog));
        assertTrue(blogDao.getAllUnapprovedPosts().contains(blog2));
    }
    
    @Test
    public void testGetBlogsByCategory() {
        Image image = imageArray[0];
        imageDao.addImage(image);
        User user = userArray[0];
        userDao.addUser(user);
        Blog blog = blogArray[0];
        blog.setUser(user);
        Tag tag = tagArray[0];
        tagDao.addTag(tag);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        blog.setHashtags(tags);
        Comment comment = commentArray[0];
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        blog.setComments(comments);
        blog.setImage(image);
        Category cat = categoryArray[0];
        List<Category> cats = new ArrayList<>();
        cats.add(cat);
        blog.setCategories(cats);
        categoryDao.addCategory(cat);
        blogDao.addBlog(blog);
        commentDao.addComment(comment, blog.getBlogId());
        
        Blog blog2 = blogArray[1];
        Tag tag2 = tagArray[1];
        tagDao.addTag(tag2);
        List<Tag> tags2 = new ArrayList<>();
        tags2.add(tag2);
        blog2.setHashtags(tags2);
        blog2.setUser(user);
        Comment com2 = commentArray[1];
        List<Comment> comm2 = new ArrayList<>();
        comm2.add(com2);
        blog2.setComments(comm2);
        blog2.setImage(image);
        Category cat2 = categoryArray[1];
        List<Category> cats2 = new ArrayList<>();
        cats2.add(cat2);
        blog2.setCategories(cats2);
        categoryDao.addCategory(cat2);
        blogDao.addBlog(blog2);
        commentDao.addComment(com2, blog2.getBlogId());
        assertEquals(1, blogDao.getBlogsByCategory(cat).size());
        assertTrue(blogDao.getBlogsByCategory(cat).contains(blog));
        assertEquals(1, blogDao.getBlogsByCategory(cat2).size());
        assertTrue(blogDao.getBlogsByCategory(cat2).contains(blog2));
    }

    @Test
    public void testGetAllApprovedBlogsByUser() {
        Image image = imageArray[0];
        imageDao.addImage(image);
        User user = userArray[0];
        userDao.addUser(user);
        Blog blog = blogArray[0];
        blog.setUser(user);
        Tag tag = tagArray[0];
        tagDao.addTag(tag);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        blog.setHashtags(tags);
        Comment comment = commentArray[0];
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        blog.setComments(comments);
        blog.setImage(image);
        Category cat = categoryArray[0];
        List<Category> cats = new ArrayList<>();
        cats.add(cat);
        blog.setCategories(cats);
        categoryDao.addCategory(cat);
        blogDao.addBlog(blog);
        commentDao.addComment(comment, blog.getBlogId());
        
        Blog blog2 = blogArray[1];
        Tag tag2 = tagArray[1];
        tagDao.addTag(tag2);
        List<Tag> tags2 = new ArrayList<>();
        tags2.add(tag2);
        blog2.setHashtags(tags2);
        blog2.setUser(user);
        Comment com2 = commentArray[1];
        List<Comment> comm2 = new ArrayList<>();
        comm2.add(com2);
        blog2.setComments(comm2);
        blog2.setImage(image);
        blog2.setApproved(false);
        Category cat2 = categoryArray[1];
        List<Category> cats2 = new ArrayList<>();
        cats2.add(cat2);
        blog2.setCategories(cats2);
        categoryDao.addCategory(cat2);
        blogDao.addBlog(blog2);
        commentDao.addComment(com2, blog2.getBlogId());
        
        User user2 = userArray[1];
        userDao.addUser(user2);
        Blog blog3 = blogArray[2];
        blog3.setUser(user2);
        blog3.setImage(image);
        blog3.setHashtags(tags);
        Comment comment3 = new Comment();
        comment3.setAuthor("Jim");
        comment3.setDate(LocalDate.parse("2017-01-01"));
        comment3.setFlagged(false);
        comment3.setComment("Comment Three");
        List<Comment> comments3 = new ArrayList<>();
        comments3.add(comment3);
        blog3.setComments(comments3);
        blog3.setCategories(cats);
        blogDao.addBlog(blog3);
        
        List<Blog> blogs = blogDao.getApprovedBlogsByUser(user.getUserId());
        assertEquals(1, blogs.size());
        assertTrue(blogs.contains(blog));
        assertFalse(blogs.contains(blog2));
        assertFalse(blogs.contains(blog3));
    }
    
    @Test
    public void testGetAllPendingBlogsByUser() {
        Image image = imageArray[0];
        imageDao.addImage(image);
        User user = userArray[0];
        userDao.addUser(user);
        Blog blog = blogArray[0];
        blog.setUser(user);
        Tag tag = tagArray[0];
        tagDao.addTag(tag);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        blog.setHashtags(tags);
        Comment comment = commentArray[0];
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        blog.setComments(comments);
        blog.setImage(image);
        Category cat = categoryArray[0];
        List<Category> cats = new ArrayList<>();
        cats.add(cat);
        blog.setCategories(cats);
        categoryDao.addCategory(cat);
        blogDao.addBlog(blog);
        commentDao.addComment(comment, blog.getBlogId());
        
        Blog blog2 = blogArray[1];
        Tag tag2 = tagArray[1];
        tagDao.addTag(tag2);
        List<Tag> tags2 = new ArrayList<>();
        tags2.add(tag2);
        blog2.setHashtags(tags2);
        blog2.setUser(user);
        Comment com2 = commentArray[1];
        List<Comment> comm2 = new ArrayList<>();
        comm2.add(com2);
        blog2.setComments(comm2);
        blog2.setImage(image);
        blog2.setApproved(false);
        blog2.setViewed(false);
        Category cat2 = categoryArray[1];
        List<Category> cats2 = new ArrayList<>();
        cats2.add(cat2);
        blog2.setCategories(cats2);
        categoryDao.addCategory(cat2);
        blogDao.addBlog(blog2);
        commentDao.addComment(com2, blog2.getBlogId());
        
        User user2 = userArray[1];
        userDao.addUser(user2);
        Blog blog3 = blogArray[2];
        blog3.setUser(user2);
        blog3.setImage(image);
        blog3.setHashtags(tags);
        Comment comment3 = new Comment();
        comment3.setAuthor("Jim");
        comment3.setDate(LocalDate.parse("2017-01-01"));
        comment3.setFlagged(false);
        comment3.setComment("Comment Three");
        List<Comment> comments3 = new ArrayList<>();
        comments3.add(comment3);
        blog3.setComments(comments3);
        blog3.setCategories(cats);
        blog3.setApproved(false);
        blog3.setViewed(false);
        blogDao.addBlog(blog3);
        
        List<Blog> blogs = blogDao.getAllPendingBlogsByUser(user.getUserId());
        assertEquals(1, blogs.size());
        assertFalse(blogs.contains(blog));
        assertTrue(blogs.contains(blog2));
        assertFalse(blogs.contains(blog3));
    }
    
    @Test
    public void testGetAllRejectedPostsByUser() {
        Image image = imageArray[0];
        imageDao.addImage(image);
        User user = userArray[0];
        userDao.addUser(user);
        Blog blog = blogArray[0];
        blog.setUser(user);
        Tag tag = tagArray[0];
        tagDao.addTag(tag);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        blog.setHashtags(tags);
        Comment comment = commentArray[0];
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        blog.setComments(comments);
        blog.setImage(image);
        Category cat = categoryArray[0];
        List<Category> cats = new ArrayList<>();
        cats.add(cat);
        blog.setCategories(cats);
        categoryDao.addCategory(cat);
        blogDao.addBlog(blog);
        commentDao.addComment(comment, blog.getBlogId());
        
        Blog blog2 = blogArray[1];
        Tag tag2 = tagArray[1];
        tagDao.addTag(tag2);
        List<Tag> tags2 = new ArrayList<>();
        tags2.add(tag2);
        blog2.setHashtags(tags2);
        blog2.setUser(user);
        Comment com2 = commentArray[1];
        List<Comment> comm2 = new ArrayList<>();
        comm2.add(com2);
        blog2.setComments(comm2);
        blog2.setImage(image);
        blog2.setApproved(false);
        blog2.setViewed(true);
        Category cat2 = categoryArray[1];
        List<Category> cats2 = new ArrayList<>();
        cats2.add(cat2);
        blog2.setCategories(cats2);
        categoryDao.addCategory(cat2);
        blogDao.addBlog(blog2);
        commentDao.addComment(com2, blog2.getBlogId());
        
        User user2 = userArray[1];
        userDao.addUser(user2);
        Blog blog3 = blogArray[2];
        blog3.setUser(user2);
        blog3.setImage(image);
        blog3.setHashtags(tags);
        Comment comment3 = new Comment();
        comment3.setAuthor("Jim");
        comment3.setDate(LocalDate.parse("2017-01-01"));
        comment3.setFlagged(false);
        comment3.setComment("Comment Three");
        List<Comment> comments3 = new ArrayList<>();
        comments3.add(comment3);
        blog3.setComments(comments3);
        blog3.setCategories(cats);
        blog3.setApproved(false);
        blog3.setViewed(false);
        blogDao.addBlog(blog3);
        
        List<Blog> blogs = blogDao.getAllRejectedBlogs(user.getUserId());
        assertEquals(1, blogs.size());
        assertFalse(blogs.contains(blog));
        assertTrue(blogs.contains(blog2));
        assertFalse(blogs.contains(blog3));
    }
    
    @Test
    public void testGetAllPendingBlogs() {
        Image image = imageArray[0];
        imageDao.addImage(image);
        User user = userArray[0];
        userDao.addUser(user);
        Blog blog = blogArray[0];
        blog.setUser(user);
        Tag tag = tagArray[0];
        tagDao.addTag(tag);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        blog.setHashtags(tags);
        Comment comment = commentArray[0];
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        blog.setComments(comments);
        blog.setImage(image);
        Category cat = categoryArray[0];
        List<Category> cats = new ArrayList<>();
        cats.add(cat);
        blog.setCategories(cats);
        categoryDao.addCategory(cat);
        blogDao.addBlog(blog);
        commentDao.addComment(comment, blog.getBlogId());
        
        Blog blog2 = blogArray[1];
        Tag tag2 = tagArray[1];
        tagDao.addTag(tag2);
        List<Tag> tags2 = new ArrayList<>();
        tags2.add(tag2);
        blog2.setHashtags(tags2);
        blog2.setUser(user);
        Comment com2 = commentArray[1];
        List<Comment> comm2 = new ArrayList<>();
        comm2.add(com2);
        blog2.setComments(comm2);
        blog2.setImage(image);
        blog2.setApproved(false);
        blog2.setViewed(false);
        Category cat2 = categoryArray[1];
        List<Category> cats2 = new ArrayList<>();
        cats2.add(cat2);
        blog2.setCategories(cats2);
        categoryDao.addCategory(cat2);
        blogDao.addBlog(blog2);
        commentDao.addComment(com2, blog2.getBlogId());
        
        List<Blog> blogs = blogDao.getAllPendingBlogs();
        assertEquals(1, blogs.size());
        assertTrue(blogs.contains(blog2));
        assertFalse(blogs.contains(blog));
    }
}