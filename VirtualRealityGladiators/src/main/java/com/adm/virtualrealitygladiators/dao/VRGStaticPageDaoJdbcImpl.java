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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author adam-
 */
public class VRGStaticPageDaoJdbcImpl implements VRGStaticPageDao {

    private JdbcTemplate template;

    public void setJdbcTemplate(JdbcTemplate template) {
        this.template = template;
    }

    private static final String INSERT_STATIC_PAGE
            = "insert into staticpages (post, title, date, userId) "
            + "values (?, ?, ?, ?)";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addStaticPage(StaticPage page) {
        template.update(INSERT_STATIC_PAGE,
                page.getPost(),
                page.getTitle(),
                page.getDate().toString(),
                page.getUser().getUserId());
        int pageId = template.queryForObject("select LAST_INSERT_ID()", Integer.class);
        page.setStaticPageId(pageId);
    }

    private static final String DELETE_STATIC_PAGE
            = "delete from staticpages where staticPageId = ?";

    @Override
    public void removeStaticPage(int pageId) {
        template.update(DELETE_STATIC_PAGE, pageId);
    }

    private static final String UPDATE_STATIC_PAGE
            = "update staticpages set post = ?, title = ?, date = ?, userId = ? "
            + "where staticpageId = ?";

    @Override
    public void updateStaticPage(StaticPage page) {
        template.update(UPDATE_STATIC_PAGE,
                page.getPost(),
                page.getTitle(),
                page.getDate().toString(),
                page.getUser().getUserId(),
                page.getStaticPageId());
    }

    private static final String FIND_USER_FOR_STATIC_PAGE
            = "select u.userId, u.userName, u.userPassword, u.enabled, u.authority from users u "
            + "inner join staticpages sp on sp.userId = u.userId "
            + "where staticpageId = ?";

    private StaticPage associateUserWithStaticPage(StaticPage page) {
        User user = new User();
        try {
            user = template.queryForObject(FIND_USER_FOR_STATIC_PAGE, new UserMapper(), page.getStaticPageId());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        page.setUser(user);
        return page;
    }

    private static final String SELECT_STATIC_PAGE_BY_ID
            = "select * from staticpages where staticpageId = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public StaticPage getStaticPageById(int pageId) {
        StaticPage page = new StaticPage();
        try {
            page = template.queryForObject(SELECT_STATIC_PAGE_BY_ID, new StaticPageMapper(), pageId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return associateUserWithStaticPage(page);
    }

    private static final String SELECT_ALL_STATIC_PAGES
            = "select * from staticpages order by date";
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<StaticPage> getAllStaticPages() {
        List<StaticPage> pages = template.query(SELECT_ALL_STATIC_PAGES, new StaticPageMapper());
        for (StaticPage page : pages) {
            associateUserWithStaticPage(page);
        }
        return pages;
    }

    private static final String SELECT_PAGES_BY_DATE
            = "select * from staticpages where date = ?";
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<StaticPage> getStaticPagesByDate(LocalDate date) {
        List<StaticPage> pages = template.query(SELECT_PAGES_BY_DATE, new StaticPageMapper(), date.toString());
        for (StaticPage page : pages) {
            associateUserWithStaticPage(page);
        }
        return pages;
    }

    private static final String SELECT_PAGES_BY_USER
            = "select * from staticpages where userId = ?";
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<StaticPage> getStaticPagesByUser(User user) {
        List<StaticPage> pages = template.query(SELECT_PAGES_BY_USER, new StaticPageMapper(), user.getUserId());
        for (StaticPage page : pages) {
            associateUserWithStaticPage(page);
        }
        return pages;
    }

    private static final String SELECT_STATIC_PAGE_BY_NAME
            = "select * from staticpages where title = ?";
    @Override
    public StaticPage getStaticPageByName(String name) {
        StaticPage page = new StaticPage();
        try {
            page = template.queryForObject(SELECT_STATIC_PAGE_BY_NAME, new StaticPageMapper(), name);
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
        return associateUserWithStaticPage(page);
    }

}
