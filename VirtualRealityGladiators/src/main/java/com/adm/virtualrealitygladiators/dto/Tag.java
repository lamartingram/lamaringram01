/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adm.virtualrealitygladiators.dto;

import java.util.Objects;

/**
 *
 * @author adam-
 */
public class Tag {
    
    private int tagId;
    private String hashtag;

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.tagId;
        hash = 83 * hash + Objects.hashCode(this.hashtag);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tag other = (Tag) obj;
        if (this.tagId != other.tagId) {
            return false;
        }
        if (!Objects.equals(this.hashtag, other.hashtag)) {
            return false;
        }
        return true;
    }
    
    
    
}
