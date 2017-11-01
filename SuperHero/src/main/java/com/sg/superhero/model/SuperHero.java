/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.model;

import java.util.Objects;

/**
 *
 * @author apprentice
 */
public class SuperHero {

    private int superHeroID;
    private String superHeroName;
    private String description;
    private SuperPower superPower;

    public int getSuperHeroID() {
        return superHeroID;
    }

    public void setSuperHeroID(int superHeroID) {
        this.superHeroID = superHeroID;
    }

    public String getSuperHeroName() {
        return superHeroName;
    }

    public void setSuperHeroName(String superHeroName) {
        this.superHeroName = superHeroName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SuperPower getSuperPower() {
        return superPower;
    }

    public void setSuperPower(SuperPower superPower) {
        this.superPower = superPower;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.superHeroID;
        hash = 67 * hash + Objects.hashCode(this.superHeroName);
        hash = 67 * hash + Objects.hashCode(this.description);
        hash = 67 * hash + Objects.hashCode(this.superPower);
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
        final SuperHero other = (SuperHero) obj;
        if (this.superHeroID != other.superHeroID) {
            return false;
        }
        if (!Objects.equals(this.superHeroName, other.superHeroName)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.superPower, other.superPower)) {
            return false;
        }
        return true;
    }

    
    
}
