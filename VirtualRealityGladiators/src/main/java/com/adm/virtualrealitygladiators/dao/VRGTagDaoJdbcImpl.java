/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adm.virtualrealitygladiators.dao;

import com.adm.virtualrealitygladiators.dto.Tag;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author adam-
 */
public class VRGTagDaoJdbcImpl implements VRGTagDao {

    private JdbcTemplate template;

    public void setJdbcTemplate(JdbcTemplate template) {
        this.template = template;
    }

    private static final String INSERT_TAG
            = "insert into tags (hashtag) "
            + "values (?)";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addTag(Tag tag) {
        template.update(INSERT_TAG,
                tag.getHashtag());
        int tagId = template.queryForObject("select LAST_INSERT_ID()", Integer.class);
        tag.setTagId(tagId);
    }

    private static final String DELETE_BLOGS_TAGS
            = "delete from blogstags where tagId = ?";
    private static final String DELETE_TAG
            = "delete from tags where tagId = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void removeTag(int tagId) {
        template.update(DELETE_BLOGS_TAGS, tagId);
        template.update(DELETE_TAG, tagId);
    }

    private static final String UPDATE_TAG
            = "update tags set hashtag = ? "
            + "where tagId = ?";

    @Override
    public void updateTag(Tag tag) {
        template.update(UPDATE_TAG,
                tag.getHashtag(),
                tag.getTagId());
    }

    private static final String SELECT_TAG_BY_ID
            = "select * from tags where tagId = ?";

    @Override
    public Tag getTagById(int tagId) {
        try {
            return template.queryForObject(SELECT_TAG_BY_ID, new TagMapper(), tagId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static final String SELECT_ALL_TAGS
            = "select * from tags";
    @Override
    public List<Tag> getAllTags() {
        return template.query(SELECT_ALL_TAGS, new TagMapper());
    }

    private static final String SELECT_TAGS_BY_BLOGID
            = "select t.tagId, t.hashtag from tags t "
            + "inner join blogstags bt on t.tagId = bt.tagId "
            + "where bt.blogId = ?";
    @Override
    public List<Tag> getTagsByBlogId(int blogId) {
        return template.query(SELECT_TAGS_BY_BLOGID, new TagMapper(), blogId);
    }

}
