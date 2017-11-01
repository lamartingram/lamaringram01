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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class SuperHeroJdbcImpl implements SuperHeroDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    
    //PREPARED STATEMENTS
    
  //Location
    private static final String SQL_INSERT_LOCATION
            = "insert into location (loc_name, description, street, city, state, zipcode, country, latitude, longitude)"
            + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_LOCATION
            = "delete from location where location_id = ?";

    private static final String SQL_UPDATE_LOCATION
            = "update location set loc_name=?, description=?, street=?, city=?, state=?, zipcode=?, country=?, latitude=?, longitude=? "
            + " where location_id  =  ?";

    private static final String SQL_SELECT_LOCATION
            = "select * from location where location_id = ?";

    private static final String SQL_SELECT_ALL_LOCATION
            = "select * from location";
    //ADDITIONAL
    private static final String SELECT_LOCATIONS_BY_SUPERHERO_ID
            = "select loc.location_id, loc.loc_name, loc.description, loc.street, loc.city, loc.state, loc.zipcode, loc.country, loc.latitude, loc.longitude"
            + " from location loc"
            + " inner join sighting si on loc.location_id = si.location_id"
            + " inner join super_hero_sighting shs on si.sighting_id = shs.sighting_id"
            + " inner join super_hero sh on shs.super_hero_id = sh.super_hero_id"
            + " where sh.super_hero_id  =  ?";


    //SELECT LOCATION BY ORG ID
    private static final String SELECT_LOCATION_BY_ORGID
            = "select loc.location_id, loc.loc_name, loc.description, loc.street, loc.city, loc.state, loc.zipcode, loc.country, loc.latitude, loc.longitude"
            + " from location loc"
            + " inner join organization org ON loc.location_id = org.location_id"
            + " where org.organization_id = ?";

    //SELECT LOCATION BY SIGHTING ID
    private static final String SELECT_LOCATION_BY_SIGHTING_ID
            = "select loc.location_id, loc.loc_name, loc.description, loc.street, loc.city, loc.state, loc.zipcode, loc.country, loc.latitude, loc.longitude"
            + " from location loc"
            + " inner join sighting si ON loc.location_id = si.location_id"
            + " where si.sighting_id = ?";
    //Organizations
    //CRUD
    private static final String SQL_INSERT_ORGANIZATION
            = "insert into organization (org_name, description, phone_number, email, location_id)"
            + " values (?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_ORGANIZATION
            = "delete from organization where organization_id = ?";

    private static final String SQL_UPDATE_ORGANIZATION
            = "update organization set org_name=?, description=?, phone_number=?, email=?, location_id=? "
            + " where organization_id  =  ?";

    private static final String SQL_SELECT_ORGANIZATION
            = "select * from organization where organization_id = ?";

    private static final String SQL_SELECT_ALL_ORGANIZATIONS
            = "select * from organization";
    //ADDITIONAL
    private static final String SELECT_ORGANIZATIONS_BY_SUPERHERO
            = "select org.organization_id, org.org_name, org.description, org.phone_number, org.email, org.location_id"
            + " from organization org"
            + " inner join memberships memb on org.organization_id = memb.organization_id"
            + " inner join super_hero sh on memb.super_hero_id = sh.super_hero_id"
            + " where sh.super_hero_id =  ?";

    //Sightings
    //CRUD
    private static final String SQL_INSERT_SIGHTING
            = "insert into sighting (location_id, sighting_id)"
            + " values (?, ?)";

    private static final String SQL_DELETE_SIGHTING
            = "delete from sighting where sighting_id = ?";

    private static final String SQL_UPDATE_SIGHTING
            = "update sighting set location_id=?, sighting_date=?"
            + " where sighting_id  =  ?";

    private static final String SQL_SELECT_SIGHTING
            = "select * from sighting where sighting_id = ?";

    private static final String SQL_SELECT_ALL_SIGHTINGS
            = "select * from sighting";
    //ADDITIONAL
    private static final String SQL_SELECT_SIGHTINGS_BY_DATE
            = "select * from sighting si"
            + " where si.sighting_date  =  ?";

    //SuperHero
    //CRUD
    private static final String SQL_INSERT_SUPERHERO
            = "insert into super_hero (super_hero_name, description, super_power_id)"
            + " values (?, ?, ?)";

    private static final String SQL_DELETE_SUPERHERO
            = "delete from super_hero where super_hero_id = ?";

    private static final String SQL_UPDATE_SUPERHERO
            = "update super_hero set super_hero_name=?, description=?, super_power_id=?"
            + " where super_hero_id  =  ?";

    private static final String SQL_SELECT_SUPERHERO
            = "select * from super_hero where super_hero_id = ?";

    private static final String SQL_SELECT_ALL_SUPERHERO
            = "select * from super_hero";

    //ADDITIONAL
    private static final String SELECT_SUPERHEROS_BY_LOC_ID
            = "select sh.super_hero_id, sh.super_hero_name, sh.description, sh.super_power_id, si.sighting_date"
            + " from location loc"
            + " inner join sighting si on loc.location_id = si.location_id"
            + " inner join super_hero_sighting shs on si.sighting_id = shs.sighting_id"
            + " inner join super_hero sh on shs.super_hero_id = sh.super_hero_id"
            + " where si.location_id  =  ?";

    private static final String SELECT_SUPERHEROS_BY_ORG_ID
            = "select sh.super_hero_id, sh.super_hero_name, sh.description, sh.super_power_id"
            + " from super_hero sh"
            + " inner join memberships memb on sh.super_hero_id = memb.super_hero_id"
            + " inner join organization org on memb.organization_id = org.organization_id"
            + " where org.organization_id  =  ?";

    //SuperPower
    //CRUD
    private static final String SQL_INSERT_SUPERPOWER
            = "insert into super_power (super_power)"
            + " values (?)";

    private static final String SQL_DELETE_SUPERPOWER
            = "delete from super_power where super_power_id = ?";

    private static final String SQL_UPDATE_SUPERPOWER
            = "update super_power set super_power=?"
            + " where super_power_id  =  ?";

    private static final String SQL_SELECT_SUPERPOWER
            = "select * from super_power where super_power_id = ?";

    private static final String SQL_SELECT_ALL_SUPERPOWER
            = "select * from super_power";

    //ADITIONAL
    //SELECT_SUPERPOWER_BY_SUPERHEROID
    private static final String SQL_SELECT_SUPERPOWER_BY_SUPERHEROID
            = "select sp.super_power_id, sp.super_power"
            + " from super_power sp"
            + " inner join super_hero sh on sh.super_power_id = sp.super_power_id where"
            + " sh.super_hero_id = ?";

    //SQL_INSERT_ORG_MEMEBERS
    private static final String SQL_INSERT_ORG_MEMBERS
            = "insert into memberships (organization_id, super_hero_id)"
            + " values (?,?)";
    private static final String SQL_DELETE_ORG_MEMBERS
            = "delete from memberships where organization_id = ?";
    private static final String SQL_SELECT_MEMBERS_BY_ORG_ID
            = "select sh.super_hero_id, sh.super_hero_name, sh.description, sh.super_power_id"
            + " from memberships mem"
            + " inner join super_hero sh ON sh.super_hero_id = mem.super_hero_id"
            + " where organization_id = ?";
    private static final String SQL_DELETE_ALL_MEMBERSHIPS
            = "delete from memberships";

    // SQL_INSERT_SUPERHEROS_IN_SIGHTING  
    private static final String SQL_INSERT_SUPERHEROS_IN_SIGHTING
            = "insert into super_hero_sighting (sighting_id, super_hero_id)"
            + " values (?,?)";
    //SELECT_SUPERHEROS_BY_SIGHTINGID
    private static final String SELECT_SUPERHEROS_BY_SIGHTINGID
            = "select sh.super_hero_id, sh.super_hero_name, sh.description, sh.super_power_id"
            + " from super_hero_sighting shs"
            + "  inner join super_hero sh ON sh.super_hero_id = shs.super_hero_id"
            + "  where sighting_id = ?";
    private static final String SQL_DELETE_SIGHTING_HEROS
            = "delete from super_hero_sighting where sighting_id = ?";
    private static final String SQL_DELETE_ALL_SUPER_HEROS_IN_SIGHTINGS
            = "delete from super_hero_sighting";
   
    
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)

    @Override
    public void addLocation(Location location) {
        jdbcTemplate.update(SQL_INSERT_LOCATION,
                location.getLocName(),
                location.getDescription(),
                location.getStreet(),
                location.getCity(),
                location.getState(),
                location.getZipCode(),
                location.getCountry(),
                location.getLatitude(),
                location.getLongitude());

        location.setLocationID(jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class));
    }

    @Override
    public void deleteLocation(int locationID) throws ReferencedByOtherTableException {
        try {
            jdbcTemplate.update(SQL_DELETE_LOCATION, locationID);
        } catch (DataIntegrityViolationException e) {
            throw new ReferencedByOtherTableException("This location is referenced by an org/sighting and cannot be deleted.");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateLocation(Location location) {

        jdbcTemplate.update(SQL_UPDATE_LOCATION,
                location.getLocName(),
                location.getDescription(),
                location.getStreet(),
                location.getCity(),
                location.getState(),
                location.getZipCode(),
                location.getCountry(),
                location.getLatitude(),
                location.getLongitude(),
                location.getLocationID());
    }

    @Override
    public Location getLocationByID(int locationID) {
        try {
            // get the properties from the location table
            Location location = jdbcTemplate.queryForObject(SQL_SELECT_LOCATION,
                    new LocationMapper(),
                    locationID);
            return location;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Location> getAllLocations() {
        // get all the locations
        List<Location> locList = jdbcTemplate.query(SQL_SELECT_ALL_LOCATION,
                new LocationMapper());
        return locList;
    }

    @Override
    public List<Location> getAllLocBySuperHero(int superHeroID) {
        // get all the locations by superheroID
        List<Location> locList = jdbcTemplate.query(SELECT_LOCATIONS_BY_SUPERHERO_ID,
                new LocationMapper(),
                superHeroID);
        return locList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSuperPower(SuperPower superPower) {
        jdbcTemplate.update(SQL_INSERT_SUPERPOWER,
                superPower.getSuperPower());

        int superPowerId= jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                        Integer.class
                );
        superPower.setSuperPowerID(superPowerId);
    }

    @Override
    public void deleteSuperPower(int superPowerID) throws ReferencedByOtherTableException {
        try {
            jdbcTemplate.update(SQL_DELETE_SUPERPOWER, superPowerID);
        } catch (DataIntegrityViolationException e) {
            throw new ReferencedByOtherTableException("This super power is referenced by a super hero and cannot be deleted.");

        }
    }

    @Override
    public void updateSuperPower(SuperPower superPower) {
        jdbcTemplate.update(SQL_UPDATE_SUPERPOWER,
                superPower.getSuperPower(),
                superPower.getSuperPowerID());
    }

    @Override
    public SuperPower getSuperPowerByID(int superPowerID) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_SUPERPOWER,
                    new SuperPowerMapper(),
                    superPowerID);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<SuperPower> getAllSuperPowers() {
        return jdbcTemplate.query(SQL_SELECT_ALL_SUPERPOWER,
                new SuperPowerMapper());
    }
   
    
    //THIS WILL RETURN THE SUPER POWER FOR A GIVEN SUPERHERo

    private SuperPower findSuperPowerForHero(SuperHero superHero) {
        return jdbcTemplate.queryForObject(SQL_SELECT_SUPERPOWER_BY_SUPERHEROID,
                new SuperPowerMapper(),
                superHero.getSuperHeroID());
    }
    //SUPER HERO HELPER METHOD2
    //THIS WILL ASSOCIATE THE SUPER POWER WITH THE SUPER HERO

    private SuperHero associateSuperPowerWithSupHero(SuperHero superHero) {
        superHero.setSuperPower(findSuperPowerForHero(superHero));
        //location.setCoordinate(findCoordinateForLoc(location));
        return superHero;
    }

    @Override
    public void addSuperHero(SuperHero superHero) {
        jdbcTemplate.update(SQL_INSERT_SUPERHERO,
                superHero.getSuperHeroName(),
                superHero.getDescription(),
                superHero.getSuperPower().getSuperPowerID());

        superHero
                .setSuperHeroID(jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                        Integer.class
                ));
    }
    //

    @Override
    public void deleteSuperHero(int superHeroID) {
        jdbcTemplate.update(SQL_DELETE_SUPERHERO, superHeroID);
    }
    //

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateSuperHero(SuperHero superHero) {
        jdbcTemplate.update(SQL_UPDATE_SUPERHERO,
                superHero.getSuperHeroName(),
                superHero.getDescription(),
                superHero.getSuperPower().getSuperPowerID(),
                superHero.getSuperHeroID());
    }
    //

    @Override
    public SuperHero getSuperHeroByID(int superHeroID) {
        try {
            // get the properties from the super hero table
            SuperHero superHero = jdbcTemplate.queryForObject(SQL_SELECT_SUPERHERO,
                    new SuperHeroMapper(),
                    superHeroID);
            // get the super power for this super hero and set it on the hero
            associateSuperPowerWithSupHero(superHero);
            return superHero;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    //

    @Override
    public List<SuperHero> getAllSuperHeros() {
        // get all the locations
        List<SuperHero> supHeroList = jdbcTemplate.query(SQL_SELECT_ALL_SUPERHERO,
                new SuperHeroMapper());
        for (SuperHero supHero : supHeroList) {
            associateSuperPowerWithSupHero(supHero);
        }
        return supHeroList;
    }
    //ORGANIZATIONS*******************************
    //ORGANIZATION HELPER METHOD1
    //THIS WILL RETURN THE LOCATION FOR A GIVEN ORG

    private Location findLocForOrg(Organization org) {
        Location location = jdbcTemplate.queryForObject(SELECT_LOCATION_BY_ORGID,
                new LocationMapper(),
                org.getOrganizationID());
        //associateLocationWithCoord(location);
        return location;
        //return jdbcTemplate.queryForObject(SELECT_LOCATION_BY_ORGID,
        //       new LocationMapper(),
        //      org.getOrganizationID());
    }
    //ORGANIZATION HELPER METHOD2
    //THIS WILL ASSOCIATE THE LOCATION WITH THE ORANIZATION

    private Organization associateLocationWithOrg(Organization organization) {
        organization.setLocation(findLocForOrg(organization));
        //superHero.setSuperPower(findSuperPowerForHero(superHero));
        return organization;
    }

    @Override
    public void addOrganization(Organization organization) {
        jdbcTemplate.update(SQL_INSERT_ORGANIZATION,
                organization.getOrgName(),
                organization.getDescription(),
                organization.getPhoneNumber(),
                organization.getEmail(),
                organization.getLocation().getLocationID());

        organization
                .setOrganizationID(jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                        Integer.class));
        //update memberships table
        insertOrgMembers(organization);
    }
    ////

    @Override
    public void deleteOrganization(int organizationID) {
        //DELETE MEMBERSHIPS FOR THIS ORG
        jdbcTemplate.update(SQL_DELETE_ORG_MEMBERS, organizationID);
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION, organizationID);
    }
//    
    ////

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateOrganization(Organization organization) {
        jdbcTemplate.update(SQL_UPDATE_ORGANIZATION,
                organization.getOrgName(),
                organization.getDescription(),
                organization.getPhoneNumber(),
                organization.getEmail(),
                organization.getLocation().getLocationID(),
                organization.getOrganizationID());
//DELETE ORGS MEMBERS
        jdbcTemplate.update(SQL_DELETE_ORG_MEMBERS, organization.getOrganizationID());
        //RESET ORGS MEMBERS
        insertOrgMembers(organization);
    }
    ////

    @Override
    public Organization getOrganizationByID(int organizationID) {
        try {
            Organization organization = jdbcTemplate.queryForObject(SQL_SELECT_ORGANIZATION,
                    new OrganizationsMapper(),
                    organizationID);

            associateLocationWithOrg(organization);
            associateMembersWithOrg(organization);
            return organization;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    ////

    @Override
    public List<Organization> getAllOrganizations() {
        List<Organization> orgList = jdbcTemplate.query(SQL_SELECT_ALL_ORGANIZATIONS,
                new OrganizationsMapper());
        for (Organization org : orgList) {
            associateLocationWithOrg(org);
            associateMembersWithOrg(org);
        }
        return orgList;
    }

    //SIGHTINGS*******************************
    //SIGHTING HELPER METHOD1
    //THIS WILL RETURN THE LOCATION FOR A GIVEN SIGHTING
    private Location findLocForSight(Sighting sighting) {
        Location location = jdbcTemplate.queryForObject(SELECT_LOCATION_BY_SIGHTING_ID,
                new LocationMapper(),
                sighting.getSightingID());
        //associateLocationWithCoord(location);
        return location;
    }

    //SIGHTING HELPER METHOD2
    //THIS WILL ASSOCIATE THE LOCATION WITH THE SIGHTING
    private Sighting associateLocationWithSight(Sighting sighting) {
        sighting.setLocation(findLocForSight(sighting));
        return sighting;
    }
    //SIGHTING HELPER METHOD3
    //UPDATE SUPERHERO SIGHTING BRIDGE TABLE

    private void insertSightingHeros(Sighting sighting) {
        final int sightingId = sighting.getSightingID();
        //ADDED!!!!!****!!!!
        if (sighting.getSuperHeros() != null) {
            final List<SuperHero> superHeros = sighting.getSuperHeros();

            // Update the  bridge table with an entry for 
            // each super hero in sighting
            for (SuperHero currentHero : superHeros) {
                jdbcTemplate.update(SQL_INSERT_SUPERHEROS_IN_SIGHTING,
                        sightingId,
                        currentHero.getSuperHeroID());
            }
        }
    }

    //SIGHTING HELPER METHOD4
    //GET SUPER HEROS FOR SIHGTING - BRIDGETABEL
    private List<SuperHero> findHerosForSighting(Sighting sighting) {
        List<SuperHero> memberList;// = new ArrayList();
        //ADDED!!!!!*****!!!!!
        try {
            memberList = jdbcTemplate.query(SELECT_SUPERHEROS_BY_SIGHTINGID,
                    new SuperHeroMapper(),
                    sighting.getSightingID());

            //need to associate my super heros with  super power
            for (SuperHero currentHero : memberList) {
                associateSuperPowerWithSupHero(currentHero);
            }

            return memberList;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }

    }

    //SUPERHERO HELPER METHOD5
    //THIS WILL ASSOCIATE SUPER HERO LIST WITH SIGHTING
    private Sighting associateSuperHerosWithSight(Sighting sighting) {
        //ADDED!!!!********
        if (findHerosForSighting(sighting) != null) {
            sighting.setSuperHeros(findHerosForSighting(sighting));
            //organization.setMembers(findMemberForOrg(organization));
        }
        return sighting;
    }

    public void addSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_INSERT_SIGHTING,
                sighting.getLocation().getLocationID(),
                sighting.getSightingDate().toString());
      

        sighting.setSightingID(jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class));
        //update super hero sighting table
        insertSightingHeros(sighting);
    }
    //////

    @Override
    public void deleteSighting(int sightingID) {
        //DELETE SUPER HEROS FOR SIGHTING
        jdbcTemplate.update(SQL_DELETE_SIGHTING_HEROS, sightingID);
        //DELETE SIGHTING
        jdbcTemplate.update(SQL_DELETE_SIGHTING, sightingID);
    }
   

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_UPDATE_SIGHTING,
                sighting.getLocation().getLocationID(),
                sighting.getSightingDate().toString(),
                sighting.getSightingID());

        //DELETE SIGHITNG HEROS FROM BRIDGE TABLE
        jdbcTemplate.update(SQL_DELETE_SIGHTING_HEROS, sighting.getSightingID());
        //RESET ORGS MEMBERS
        insertSightingHeros(sighting);
    }
    //////

    @Override
    public Sighting getSightingByID(int sightingID) {
        try {
            Sighting sighting = jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING,
                    new SightingMapper(),
                    sightingID);
            associateLocationWithSight(sighting);
            associateSuperHerosWithSight(sighting);
            //associateLocationWithOrg(organization);
            //associate coordinatewith location????????
            return sighting;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    //////

    @Override
    public List<Sighting> getAllSightings() {
        List<Sighting> sightingList = jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS,
                new SightingMapper());
        for (Sighting si : sightingList) {
            associateLocationWithSight(si);
            associateSuperHerosWithSight(si);
        }
        return sightingList;

    }
    //*******MEMBERSHIPS BRIDGE*******************

    @Override
    public void deleteAllMemberships() {
        jdbcTemplate.update(SQL_DELETE_ALL_MEMBERSHIPS);
    }

    //*******SUPER HERO SIGHTING BRIDGE*******************
    @Override
    public void deleteAllSuperHerosInSighting() {
        jdbcTemplate.update(SQL_DELETE_ALL_SUPER_HEROS_IN_SIGHTINGS);
    }

    //ORGANIZATION HELPER METHOD3
    //UPDATE MEMBERSHIP BRIDGE TABLE
    private void insertOrgMembers(Organization org) {
        final int orgId = org.getOrganizationID();
        //ADDED!!!!!****!!!!
        if (org.getMembers() != null) {
            final List<SuperHero> superHeroMembers = org.getMembers();

            // Update the membership bridge table with an entry for 
            // each super hero in org
            for (SuperHero currentHero : superHeroMembers) {
                jdbcTemplate.update(SQL_INSERT_ORG_MEMBERS,
                        orgId,
                        currentHero.getSuperHeroID());
            }
        }
    }

    //ORGANIZATION HELPER METHOD4
    //GET MEMBERS FOR ORG - BRIDGETABEL
    private List<SuperHero> findMemberForOrg(Organization org) {
        List<SuperHero> memberList;// = new ArrayList();
        //ADDED!!!!!*****!!!!!
        try {
            memberList = jdbcTemplate.query(SQL_SELECT_MEMBERS_BY_ORG_ID,
                    new SuperHeroMapper(),
                    org.getOrganizationID());

            //need to associate my super heros with  super power
            for (SuperHero currentHero : memberList) {
                associateSuperPowerWithSupHero(currentHero);
            }

            return memberList;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }

    }

    //ORGANIZATION HELPER METHOD5
    //THIS WILL ASSOCIATE MEMBERHIP LIST WITH ORG
    private Organization associateMembersWithOrg(Organization organization) {
        //ADDED!!!!********
        if (findMemberForOrg(organization) != null) {
            organization.setMembers(findMemberForOrg(organization));
        }
        return organization;
    }
  
    //MAPPERS
    //Location

    private static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location loc = new Location();
            loc.setLocationID(rs.getInt("location_id"));
            loc.setLocName(rs.getString("loc_Name"));
            loc.setDescription(rs.getString("description"));
            loc.setCity(rs.getString("city"));
            loc.setState(rs.getString("state"));
            loc.setZipCode(rs.getString("zipcode"));
            loc.setCountry(rs.getString("country"));
            loc.setLatitude(rs.getBigDecimal("latitude"));
            loc.setLongitude(rs.getBigDecimal("longitude"));

            return loc;
        }
    }
//*****SUPER POWER*******

    private static final class SuperPowerMapper implements RowMapper<SuperPower> {

        @Override
        public SuperPower mapRow(ResultSet rs, int i) throws SQLException {
            SuperPower supPow = new SuperPower();
            supPow.setSuperPowerID(rs.getInt("super_power_id"));
            supPow.setSuperPower(rs.getString("super_power"));

            return supPow;
        }
    }

//******SURE HERO********
    private static final class SuperHeroMapper implements RowMapper<SuperHero> {

        @Override
        public SuperHero mapRow(ResultSet rs, int i) throws SQLException {
            SuperHero supHero = new SuperHero();
            supHero.setSuperHeroID(rs.getInt("super_hero_id"));
            supHero.setSuperHeroName(rs.getString("super_hero_name"));
            supHero.setDescription(rs.getString("description"));

            //relate super power later
            return supHero;
        }

    }


    private static final class OrganizationsMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization org = new Organization();
            org.setOrganizationID(rs.getInt("organization_id"));
            org.setOrgName(rs.getString("org_Name"));
            org.setDescription(rs.getString("description"));
            org.setPhoneNumber(rs.getString("phone_number"));
            org.setEmail(rs.getString("email"));
            return org;
        }
    }

//******SIGHTINGS MAPPER********
    private static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setSightingID(rs.getInt("sighting_id"));
            sighting.setSightingDate(rs.getTimestamp("sighting_date").
                    toLocalDateTime().toLocalDate());

            Organization org = new Organization();
            //relate location later
            return sighting;
        }
    }
}
