/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;


import com.sg.superhero.model.Location;
import com.sg.superhero.model.Organization;
import com.sg.superhero.model.Sighting;
import com.sg.superhero.model.SuperHero;
import com.sg.superhero.model.SuperPower;
import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author apprentice
 */
public interface SuperHeroDao {

    //Location
    public void addLocation(Location location);

    public void deleteLocation(int locationID) throws ReferencedByOtherTableException;

    public void updateLocation(Location location);

    public Location getLocationByID(int locationID);

    public List<Location> getAllLocations();

    public List<Location> getAllLocBySuperHero(int superHeroID);

    //SuperPower
    public void addSuperPower(SuperPower superPower);

    public void deleteSuperPower(int superPowerID) throws ReferencedByOtherTableException;

    public void updateSuperPower(SuperPower superPower);

    public SuperPower getSuperPowerByID(int superPowerID);

    public List<SuperPower> getAllSuperPowers();

    //superHero
    public void addSuperHero(SuperHero superHero);

    public void deleteSuperHero(int superHeroID);

    public void updateSuperHero(SuperHero superHero);

    public SuperHero getSuperHeroByID(int superHeroID);

    public List<SuperHero> getAllSuperHeros();

    
    //Organzation
    public void addOrganization(Organization organization);

    public void deleteOrganization(int organizationID);

    public void updateOrganization(Organization organization);

    public Organization getOrganizationByID(int organizationID);

    public List<Organization> getAllOrganizations();

    //sightings
    public void addSighting(Sighting sighting);

    public void deleteSighting(int sightingID);

    public void updateSighting(Sighting sighting);

    public Sighting getSightingByID(int sightingID);

    public List<Sighting> getAllSightings();

    //Memberships Brdige
    public void deleteAllMemberships();
    //SuperHeroSightings Bridge 

    public void deleteAllSuperHerosInSighting();

}
