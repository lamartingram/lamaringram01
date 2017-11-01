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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class SuperHeroDaoTest {

    SuperHeroDao dao;

    public SuperHeroDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        dao = ctx.getBean("superHeroDao", SuperHeroDao.class);

        //delete brige table entries for super hero sighting
        dao.deleteAllSuperHerosInSighting();

        //delete all memberships from bridge table
        dao.deleteAllMemberships();
        //delete all sightings
        List<Sighting> sightings = dao.getAllSightings();
        for (Sighting currentSight : sightings) {
            dao.deleteSighting(currentSight.getSightingID());
        }

        // delete all orgs
        List<Organization> organizations = dao.getAllOrganizations();
        for (Organization currentOrg : organizations) {
            dao.deleteOrganization(currentOrg.getOrganizationID());
        }
        // delete all locations
        List<Location> locations = dao.getAllLocations();
        for (Location currentLoc : locations) {
            try {
                dao.deleteLocation(currentLoc.getLocationID());
            } catch (ReferencedByOtherTableException e) {
                System.out.println(e.getMessage());
            }
        }

        //delete all super heros
        List<SuperHero> supHeros = dao.getAllSuperHeros();
        for (SuperHero currentSupHero : supHeros) {
            dao.deleteSuperHero(currentSupHero.getSuperHeroID());
            //dao.deleteSuperPower(currentSupPow.getSuperPowerID());
        }
        //delete all super powers
        List<SuperPower> supPows = dao.getAllSuperPowers();
        for (SuperPower currentSupPow : supPows) {
            try {
                dao.deleteSuperPower(currentSupPow.getSuperPowerID());
            } catch (ReferencedByOtherTableException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void addGetLocation() {

        Location location = new Location();
        location.setLocName("Lamar's House");
        location.setDescription("Very Big");
        location.setStreet("1739 model Road");
        location.setCity("Louisville");
        location.setState("Ky");
        location.setZipCode("40216");
        location.setCountry("USA");
        location.setLatitude(new BigDecimal("4.5"));
        location.setLongitude(new BigDecimal("3.5"));
        dao.addLocation(location);
        Location locFromDao = dao.getLocationByID(location.getLocationID());
        assertEquals(locFromDao, location);
    }

    @Test
    public void deleteLocation() {

        Location location = new Location();
        location.setLocName("Lamar's House");
        location.setDescription("Very Big");
        location.setStreet("1739 model Road");
        location.setCity("Louisville");
        location.setState("Ky");
        location.setZipCode("40216");
        location.setCountry("USA");
        location.setLatitude(new BigDecimal("4.5"));
        location.setLongitude(new BigDecimal("3.5"));
        dao.addLocation(location);

        Location locFromDao = dao.getLocationByID(location.getLocationID());
        assertEquals(locFromDao, location);
        try {
            dao.deleteLocation(location.getLocationID());
        } catch (ReferencedByOtherTableException e) {
            System.out.println(e.getMessage());
        }

        assertNull(dao.getLocationByID(location.getLocationID()));
    }

    @Test
    public void deleteLocationREF() {
        boolean expectedResult = false;

        Location location = new Location();
        location.setLocName("Lamar's House");
        location.setDescription("Very Big");
        location.setStreet("1739 model Road");
        location.setCity("Louisville");
        location.setState("Ky");
        location.setZipCode("40216");
        location.setCountry("USA");
        location.setLatitude(new BigDecimal("4.5"));
        location.setLongitude(new BigDecimal("3.5"));

        dao.addLocation(location);
        //ADD SIGHTING TO REF LOC
        Sighting sighting = new Sighting();
        sighting.setLocation(location);
        sighting.setSightingDate(LocalDate.now());
        dao.addSighting(sighting);
        try {
            dao.deleteLocation(location.getLocationID());
            expectedResult = false;
        } catch (ReferencedByOtherTableException e) {
            expectedResult = true;
            System.out.println(e.getMessage());
        }

        assertTrue(expectedResult);

    }

    @Test
    public void updateLocation() {

        Location location = new Location();
        location.setLocName("Lamar's House");
        location.setDescription("Very Big");
        location.setStreet("1739 model Road");
        location.setCity("Louisville");
        location.setState("Ky");
        location.setZipCode("40216");
        location.setCountry("USA");
        location.setLatitude(new BigDecimal("4.5"));
        location.setLongitude(new BigDecimal("3.5"));

        dao.addLocation(location);
        location.setDescription("The best house!");
        dao.updateLocation(location);
        Location locFromDao = dao.getLocationByID(location.getLocationID());
        assertEquals(locFromDao.getDescription(), "The best house!");
    }

    @Test
    public void getAllLocations() {

       
           Location location = new Location();
        location.setLocName("Lamar's Housde");
        location.setDescription("Very Big");
        location.setStreet("1739 model Road");
        location.setCity("Louisville");
        location.setState("Ky");
        location.setZipCode("40216");
        location.setCountry("USA");
        location.setLatitude(new BigDecimal("4.5"));
        location.setLongitude(new BigDecimal("3.5"));
         dao.addLocation(location);

        Location location2 = new Location();
        location2.setLocName("Lamar's Second");
        location2.setDescription("Very samll");
        location2.setStreet("NA");
        location2.setCity("Louisville");
        location2.setState("Ky ");
        location2.setZipCode("40216");
        location2.setCountry("USA");
        location2.setLatitude(new BigDecimal("23.55"));
        location2.setLongitude(new BigDecimal("34.00"));

        dao.addLocation(location2);
        assertEquals(dao.getAllLocations().size(), 2);
    }

    //**********SUPER POWER*************
    @Test
    public void addGetSuperPower() {
        SuperPower superPower = new SuperPower();
        superPower.setSuperPower("flys");
        dao.addSuperPower(superPower);
        SuperPower supPowFromDao = dao.getSuperPowerByID(superPower.getSuperPowerID());
        assertEquals(supPowFromDao, superPower);
    }

    @Test

    public void deleteSuperPower() {
        //1. TEST DELETE super POWER WITHOUT SUPER HERO REFERENCE
        SuperPower superPower = new SuperPower();
        superPower.setSuperPower("jumps");
        dao.addSuperPower(superPower);
        try {
            dao.deleteSuperPower(superPower.getSuperPowerID());
        } catch (ReferencedByOtherTableException e) {
        }

        assertNull(dao.getSuperPowerByID(superPower.getSuperPowerID()));

    }

    @Test
    public void deleteSuperPowerREF() {
        //1. TEST DELETE super POWER WITH SUPER HERO REFERENCE
        Boolean expectedResult = false;

        SuperPower superPower = new SuperPower();
        superPower.setSuperPower("jumps");
        dao.addSuperPower(superPower);
        SuperHero superHero = new SuperHero();
        superHero.setSuperHeroName("Flash");
        superHero.setDescription("Very fast");
        superHero.setSuperPower(superPower);
        dao.addSuperHero(superHero);

        try {
            dao.deleteSuperPower(superPower.getSuperPowerID());
            expectedResult = false;
        } catch (ReferencedByOtherTableException e) {
            expectedResult = true;
            System.out.println(e.getMessage());
        }

        assertTrue(expectedResult);

    }

    @Test

    public void updateSuperPower() {
        SuperPower superPower = new SuperPower();
        superPower.setSuperPower("jumps");
        dao.addSuperPower(superPower);
        superPower.setSuperPower("jumps 2X");
        dao.updateSuperPower(superPower);
        SuperPower supPowFromDao = dao.getSuperPowerByID(superPower.getSuperPowerID());
        assertEquals(supPowFromDao.getSuperPower(), "jumps 2X");

    }

    //SKIPPING GET ALL SUPER POWERS, THIS IS TESTED IN SET UP
    //********SUPER HERP****************
    @Test
    public void addGetSuperHero() {
        SuperPower superPower = new SuperPower();
        superPower.setSuperPower("flys");
        dao.addSuperPower(superPower);

        SuperHero superHero = new SuperHero();
        superHero.setSuperHeroName("Bob");
        superHero.setDescription("old man");
        superHero.setSuperPower(superPower);
        dao.addSuperHero(superHero);

        SuperHero supHeroFromDao = dao.getSuperHeroByID(superHero.getSuperHeroID());
        assertEquals(supHeroFromDao, superHero);
    }

    @Test
    public void deleteSuperHero() {

        SuperPower superPower = new SuperPower();
        superPower.setSuperPower("flys");
        dao.addSuperPower(superPower);

        SuperHero superHero = new SuperHero();
        superHero.setSuperHeroName("Bob");
        superHero.setDescription("old man");
        superHero.setSuperPower(superPower);
        dao.addSuperHero(superHero);

        //        Location locFromDao = dao.getLocationByID(location.getLocationID());
        //        assertEquals(locFromDao, location);
        dao.deleteSuperHero(superHero.getSuperHeroID());
        //dao.deleteLocation(location.getLocationID());

        assertNull(dao.getSuperHeroByID(superHero.getSuperHeroID()));
    }
    //

    @Test
    public void updateSuperHero() {
        SuperPower superPower = new SuperPower();
        superPower.setSuperPower("flys");
        dao.addSuperPower(superPower);

        SuperHero superHero = new SuperHero();
        superHero.setSuperHeroName("Bob");
        superHero.setDescription("old man");
        superHero.setSuperPower(superPower);
        dao.addSuperHero(superHero);

        superHero.setSuperHeroName("Bobby");
        dao.updateSuperHero(superHero);

        SuperHero supHeroFromDao = dao.getSuperHeroByID(superHero.getSuperHeroID());
        assertEquals(supHeroFromDao.getSuperHeroName(), "Bobby");

    }

    //********ORGANIATIONS****************
    @Test
    public void addGetOrgs() {

        Location location = new Location();
        location.setLocName("My House");
        location.setDescription("A house");
        location.setStreet("1010 E Oak");
        location.setCity("Louisville");
        location.setState("Ky");
        location.setZipCode("40204");
        location.setCountry("USA");
        location.setLatitude(new BigDecimal("34.55"));
        location.setLongitude(new BigDecimal("34.22"));

        dao.addLocation(location);

        Organization org = new Organization();
        org.setOrgName("My Org");
        org.setDescription("The best");
        org.setPhoneNumber("256-679-9079");
        org.setEmail("sauer@gmail.com");
        org.setLocation(location);

        //ADD SUPER HERO TO ORG MEMBERSHIPS
        SuperPower superPower = new SuperPower();
        superPower.setSuperPower("flys");
        dao.addSuperPower(superPower);
        SuperHero superHero = new SuperHero();
        superHero.setSuperHeroName("Bob");
        superHero.setDescription("old man");
        superHero.setSuperPower(superPower);
        dao.addSuperHero(superHero);

        List<SuperHero> members = new ArrayList();
        members.add(superHero);

        org.setMembers(members);

        dao.addOrganization(org);

        Organization orgFromDao = dao.getOrganizationByID(org.getOrganizationID());
        assertEquals(org, orgFromDao);
    }

    @Test

    public void addGetOrgsNOMemb() {

        Location location = new Location();
        location.setLocName("My House");
        location.setDescription("A house");
        location.setStreet("1010 E Oak");
        location.setCity("Louisville");
        location.setState("Ky");
        location.setZipCode("40204");
        location.setCountry("USA");
        location.setLatitude(new BigDecimal("3.44"));
        location.setLongitude(new BigDecimal("34.66"));

        dao.addLocation(location);

        Organization org = new Organization();
        org.setOrgName("My Org");
        org.setDescription("The best");
        org.setPhoneNumber("256-679-9079");
        org.setEmail("sauer@gmail.com");
        org.setLocation(location);

        //ADD SUPER HERO TO ORG MEMBERSHIPS
        SuperPower superPower = new SuperPower();
        superPower.setSuperPower("flys");
        dao.addSuperPower(superPower);

        SuperHero superHero = new SuperHero();
        superHero.setSuperHeroName("Bob");
        superHero.setDescription("old man");
        superHero.setSuperPower(superPower);
        dao.addSuperHero(superHero);

        List<SuperHero> members = new ArrayList();
        members.add(superHero);

        org.setMembers(members);
        dao.addOrganization(org);

        Organization orgFromDao = dao.getOrganizationByID(org.getOrganizationID());
        assertEquals(org, orgFromDao);
    }

    @Test
    public void deleteOrg() {

        Location location = new Location();
        location.setLocName("My House");
        location.setDescription("A house");
        location.setStreet("1010 E Oak");
        location.setCity("Louisville");
        location.setState("Ky");
        location.setZipCode("40204");
        location.setCountry("USA");
        location.setLatitude(new BigDecimal("234.4"));
        location.setLongitude(new BigDecimal("23.56"));

        dao.addLocation(location);

        Organization org = new Organization();
        org.setOrgName("My Org");
        org.setDescription("The best");
        org.setPhoneNumber("256-679-9079");
        org.setEmail("sauer@gmail.com");
        org.setLocation(location);

        dao.addOrganization(org);

        dao.deleteOrganization(org.getOrganizationID());

        assertNull(dao.getOrganizationByID(org.getOrganizationID()));
    }
    ////

    @Test
    public void updateOrg() {

        Location location = new Location();
        location.setLocName("My House");
        location.setDescription("A house");
        location.setStreet("1010 E Oak");
        location.setCity("Louisville");
        location.setState("Ky");
        location.setZipCode("40204");
        location.setCountry("USA");
        location.setLatitude(new BigDecimal("34.56"));
        location.setLongitude(new BigDecimal("23.67"));

        dao.addLocation(location);

        Organization org = new Organization();
        org.setOrgName("My Org");
        org.setDescription("The best");
        org.setPhoneNumber("256-679-9079");
        org.setEmail("sauer@gmail.com");
        org.setLocation(location);
        //ADD SUPER HERO TO ORG MEMBERSHIPS
        SuperPower superPower = new SuperPower();
        superPower.setSuperPower("flys");
        dao.addSuperPower(superPower);

        SuperHero superHero = new SuperHero();
        superHero.setSuperHeroName("Bob");
        superHero.setDescription("old man");
        superHero.setSuperPower(superPower);
        dao.addSuperHero(superHero);

        List<SuperHero> members = new ArrayList();
        members.add(superHero);

        org.setMembers(members);

        dao.addOrganization(org);
        org.setDescription("New Descrip");
        dao.updateOrganization(org);
        Organization orgFromDao = dao.getOrganizationByID(org.getOrganizationID());
        assertEquals(orgFromDao.getDescription(), "New Descrip");

//        //TRY UPDATING LOC
        Location location2 = new Location();
        location2.setLocName("My House");
        location2.setDescription("A house");
        location2.setStreet("1010 E Oak");
        location2.setCity("Louisville");
        location2.setState("Ky");
        location2.setZipCode("40204");
        location2.setCountry("USA");
        location.setLatitude(new BigDecimal("34.56"));
        location.setLongitude(new BigDecimal("23.67"));

        dao.addLocation(location2);

        org.setLocation(location2);
        dao.updateOrganization(org);
        Organization orgFromDao1 = dao.getOrganizationByID(org.getOrganizationID());
        assertEquals(orgFromDao1.getLocation(), location2);

    }

    //********SIGHTINGS****************
    @Test
    public void addGetSightings() {

        Location location = new Location();
        location.setLocName("My House");
        location.setDescription("A house");
        location.setStreet("1010 E Oak");
        location.setCity("Louisville");
        location.setState("Ky");
        location.setZipCode("40204");
        location.setCountry("USA");
        location.setLatitude(new BigDecimal("34.56"));
        location.setLongitude(new BigDecimal("23.67"));

        dao.addLocation(location);

        Sighting sighting = new Sighting();
        sighting.setLocation(location);
        sighting.setSightingDate(LocalDate.now());

        dao.addSighting(sighting);

        Sighting sightFromDao = dao.getSightingByID(sighting.getSightingID());
        assertEquals(sighting, sightFromDao);
    }

    @Test
    public void deleteSighting() {

        Location location = new Location();
        location.setLocName("My House");
        location.setDescription("A house");
        location.setStreet("1010 E Oak");
        location.setCity("Louisville");
        location.setState("Ky");
        location.setZipCode("40204");
        location.setCountry("USA");
        location.setLatitude(new BigDecimal("34.56"));
        location.setLongitude(new BigDecimal("23.67"));

        dao.addLocation(location);

        Sighting sighting = new Sighting();
        sighting.setLocation(location);
        sighting.setSightingDate(LocalDate.now());

        dao.addSighting(sighting);

        dao.deleteSighting(sighting.getSightingID());

        assertNull(dao.getSightingByID(sighting.getSightingID()));
    }
    ////
    //

    @Test
    public void updateSighting() {

        Location location = new Location();
        location.setLocName("My House");
        location.setDescription("A house");
        location.setStreet("1010 E Oak");
        location.setCity("Louisville");
        location.setState("Ky");
        location.setZipCode("40204");
        location.setCountry("USA");
        location.setLatitude(new BigDecimal("34.56"));
        location.setLongitude(new BigDecimal("23.67"));

        dao.addLocation(location);

        Sighting sighting = new Sighting();
        sighting.setLocation(location);
        sighting.setSightingDate(LocalDate.now());

        dao.addSighting(sighting);

        sighting.setSightingDate(LocalDate.parse("2017-01-01"));
        dao.updateSighting(sighting);
        Sighting sightFromDao = dao.getSightingByID(sighting.getSightingID());
        assertEquals(sightFromDao.getSightingDate(), LocalDate.parse("2017-01-01"));

    }
}
