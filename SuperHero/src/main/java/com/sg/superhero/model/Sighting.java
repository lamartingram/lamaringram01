/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author apprentice
 */
public class Sighting {

    private int sightingID;
    private Location location;
    private LocalDate SightingDate;

    private List<SuperHero> superHeros;

    public Sighting() {
        superHeros = new ArrayList<>();
    }

    public int getSightingID() {
        return sightingID;
    }

    public void setSightingID(int sightingID) {
        this.sightingID = sightingID;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LocalDate getSightingDate() {
        return SightingDate;
    }

    public void setSightingDate(LocalDate SightingDate) {
        this.SightingDate = SightingDate;
    }

    public List<SuperHero> getSuperHeros() {
        return superHeros;
    }

    public void setSuperHeros(List<SuperHero> superHeros) {
        this.superHeros = superHeros;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.sightingID;
        hash = 17 * hash + Objects.hashCode(this.location);
        hash = 17 * hash + Objects.hashCode(this.SightingDate);
        hash = 17 * hash + Objects.hashCode(this.superHeros);
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
        final Sighting other = (Sighting) obj;
        if (this.sightingID != other.sightingID) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.SightingDate, other.SightingDate)) {
            return false;
        }
        if (!Objects.equals(this.superHeros, other.superHeros)) {
            return false;
        }
        return true;
    }

}
