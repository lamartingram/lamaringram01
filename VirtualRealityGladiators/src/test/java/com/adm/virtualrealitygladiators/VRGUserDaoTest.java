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
public class VRGUserDaoTest {

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

    public VRGUserDaoTest() {
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

        blogArray = new Blog[]{
            blog, blog2, blog3, blog4
        };
        
        Category category = new Category();
        category.setCategory("Category One");
        Category category2 = new Category();
        category2.setCategory("Category Two");
        
        categoryArray = new Category[]{
          category, category2  
        };
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddGetUsers() {
        User user = userArray[0];
        userDao.addUser(user);
        User fromDao = userDao.getUserById(user.getUserId());
        assertEquals(user, fromDao);
    }

    @Test
    public void testDeleteUser() {
        User user = userArray[0];
        userDao.addUser(user);
        User fromDao = userDao.getUserById(user.getUserId());
        assertEquals(user, fromDao);
        userDao.removeUser(user.getUserId());
        assertNull(userDao.getUserById(user.getUserId()));
    }

    @Test
    public void testUpdateUser() {
        User user = userArray[0];
        userDao.addUser(user);
        User user2 = userArray[1];
        user2.setUserId(user.getUserId());
        userDao.updateUser(user2);
        User fromDao = userDao.getUserById(user2.getUserId());
        assertEquals(user2, fromDao);
    }

    @Test
    public void testGetAllUsers() {
        User user = userArray[0];
        userDao.addUser(user);
        assertEquals(1, userDao.getAllUsers().size());
        User user2 = userArray[1];
        userDao.addUser(user2);
        assertEquals(2, userDao.getAllUsers().size());
        assertTrue(userDao.getAllUsers().contains(user));
        assertTrue(userDao.getAllUsers().contains(user2));
    }

    @Test
    public void testGetUserByBlogId() {
        User user = userArray[0];
        userDao.addUser(user);
        Blog blog = blogArray[0];
        blog.setUser(user);
        Tag tag = tagArray[0];
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        blog.setHashtags(tags);
        tagDao.addTag(tag);
        Image image = imageArray[0];
        imageDao.addImage(image);
        blog.setImage(image);
        Category cat = categoryArray[0];
        List<Category> cats = new ArrayList<>();
        cats.add(cat);
        blog.setCategories(cats);
        categoryDao.addCategory(cat);
        blogDao.addBlog(blog);
        User fromDao = userDao.getUserByBlogId(blog.getBlogId());
        assertEquals(user, fromDao);
    }

    @Test
    public void testGetUserByUserName() {
        User user = userArray[0];
        userDao.addUser(user);
        User user2 = userArray[1];
        userDao.addUser(user2);
        User fromDao = userDao.getUserByUserName(user.getUserName());
        assertEquals(user, fromDao);
        fromDao = userDao.getUserByUserName(user2.getUserName());
        assertEquals(user2, fromDao);
    }
}
