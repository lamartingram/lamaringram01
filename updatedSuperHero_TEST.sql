

DROP DATABASE IF EXISTS SuperHeroSightings_TEST;

CREATE DATABASE IF NOT EXISTS SuperHeroSightings_TEST;

-- SuperHero ***************
USE SuperHeroSightings_TEST;

CREATE TABLE IF NOT EXISTS `super_hero`(
`super_hero_id` INT NOT NULL auto_increment,
`super_hero_name` VARCHAR (30) NOT NULL,
`description` VARCHAR (100) NOT NULL,
`super_power_id` INT NOT NULL,
PRIMARY KEY (`super_hero_id`)
) ;

USE SuperHeroSightings_TEST;

INSERT INTO `super_hero`
(`super_hero_id`, `super_hero_name`,`description`,`super_power_id`)
VALUES 
('1','SuperMan','Black costume', '1'),
('2','Spider Man','Red custome', '2'),
('3','Wolverine','Claws', '3'),
('4','Iron Man','Awesome suit', '3'),
('5', 'Batman', 'Sweet Ride', '3');

-- SuperPower *****************
USE SuperHeroSightings_TEST;

CREATE TABLE IF NOT EXISTS `super_power`
(`super_power_id` INT NOT NULL auto_increment,
`super_power` VARCHAR (30) NOT NULL,
PRIMARY KEY (`super_power_id`)
);

USE SuperHeroSightings_TEST;

INSERT INTO `super_power`
(`super_power_id`, `super_power`)
VALUES 
('1','fly'),
('2','snorts fire'),
('3','Webs'),
('4','psychic')
;


-- Location *******************
USE SuperHeroSightings_TEST;

CREATE TABLE IF NOT EXISTS `location`
(`location_id` INT NOT NULL auto_increment,
`loc_name` VARCHAR (30) NOT NULL,
`description` VARCHAR (100) NOT NULL,
`street` VARCHAR (30) NOT NULL,
`city` VARCHAR (30) NOT NULL,
`state` VARCHAR (30) NOT NULL,
`zipcode` VARCHAR (30) NOT NULL,
`country` VARCHAR (30) NOT NUll,
`latitude` Decimal(30) NOT NUll,
`longitude` Decimal(30) NOT NULL,
PRIMARY KEY (`location_id`)
);

USE SuperHeroSightings_TEST;

INSERT INTO `location`
(`location_id`, `loc_name`,`description`,`street`,`city`,`state`,`zipcode`,`country`,`latitude`,`longitude`)
VALUES 
('1','EmpireStateBuilding','A very Tall building', '3453 york', 'NEW YORK','NY','40021','USA','11.3','22.55'),
('2','The Madness Cave','Firy cave', '5432 Bat Cave Ave', 'Louisville','Ky','40032','USA','2','43.66'),
('3','Nile River','The longest River on earth', 'Amozon', 'Louisville','Ky','40202','USA','4','567.34'),
('4','Veach Beach','A beach', '500 cal', 'Venich','CA','40023','USA','5','123.4'),
('5','Mammthod cave','A big dark scary cave', '13131 Road', 'Louisville','Ky','40204','USA','3','345.56')
;

-- Organization ******************
USE SuperHeroSightings_TEST;

CREATE TABLE IF NOT EXISTS `organization`
(`organization_id` INT NOT NULL auto_increment ,
`org_name` VARCHAR (30) NOT NULL,
`description` VARCHAR (100) NOT NULL,
`phone_number` VARCHAR (30) NOT NULL,
`email` VARCHAR (30) NOT NULL,
`location_id` INT NOT NULL,
PRIMARY KEY (`organization_id`)
);
 	 	 		
USE SuperHeroSightings_TEST;

INSERT INTO `organization`
(`organization_id`,`org_name`,`description`,`phone_number`,`email`,`location_id`)
VALUES 
('1','The fire club','Super heros that has fire', '502-222-999', 'sdaa@gmail.com','1'),
('2','The flying club','Heros that can fly', '502-444-2446', 'gn@gmail.com','2'),
('3','The strong club','Only strong super heros', '502-333-5555', 'sknflsadfn@gmail.com','3');

-- Sighting ******************
USE SuperHeroSightings_TEST;

CREATE TABLE IF NOT EXISTS `sighting`(
`sighting_id` INT NOT NULL auto_increment,
`location_id` INT NOT NULL,
`sighting_date` DATE NOT NULL,
PRIMARY KEY (`sighting_id`)
);

USE SuperHeroSightings_TEST;

INSERT INTO `sighting`
(`sighting_id`,`location_id`,`sighting_date`)
VALUES 
('1','3','2017/06/30'),
('2','1','2017/07/04'),
('3','5','2017/05/26'),
('4','3','2017/06/07');

-- SuperHeroSighting (BRIDGE) ************

USE SuperHeroSightings_TEST;

CREATE TABLE IF NOT EXISTS `super_hero_sighting`
(`sighting_id` INT NOT NULL,
`super_hero_id` INT NOT NULL,
PRIMARY KEY (`sighting_id`,`super_hero_id`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

USE SuperHeroSightings_TEST;

INSERT INTO `super_hero_sighting`
(`sighting_id`,`super_hero_id`)
VALUES 
('1','2'),
('1','1'),
('2','2'),
('3','1'),
('4','3')
;

-- Memberships (BRIDGE) ********************
USE SuperHeroSightings_TEST;

CREATE TABLE IF NOT EXISTS `memberships`
(`super_hero_id` INT NOT NULL,
`organization_id` INT NOT NULL,
PRIMARY KEY (`super_hero_iD`,`organization_id`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

USE SuperHeroSightings_TEST;

INSERT INTO `memberships`
(`super_hero_id`, `organization_id`)
VALUES 
('1','1'),
('1','2'),
('2','3'),
('2','1'),
('3','1'),
('4','3'),
('4','2');

USE SuperHeroSightings_TEST;

ALTER TABLE super_hero
ADD CONSTRAINT 
fk_super_hero_super_power
FOREIGN KEY
(super_power_id)
REFERENCES
super_power(super_power_id);


-- Organization **************
USE SuperHeroSightings_TEST;
ALTER TABLE organization
ADD CONSTRAINT 
fk_organization_location
FOREIGN KEY
(location_id)
REFERENCES
location(location_id);

-- Sighting ****************
USE SuperHeroSightings_TEST;
ALTER TABLE sighting
ADD CONSTRAINT 
fk_sighting_location
FOREIGN KEY
(location_id)
REFERENCES
location(location_id);

-- SuperHeroSightings ************
USE SuperHeroSightings_TEST;
ALTER TABLE super_hero_sighting
ADD CONSTRAINT 
fk_super_hero_sightings_sighting
FOREIGN KEY
(sighting_id)
REFERENCES
sighting(sighting_id);

USE SuperHeroSightings_TEST;
ALTER TABLE super_hero_sighting
ADD CONSTRAINT 
fk_super_hero_sightings_supe_hero
FOREIGN KEY
(super_hero_id)
REFERENCES
super_hero(super_hero_id);


-- Memberships *************
USE SuperHeroSightings_TEST;
ALTER TABLE memberships
ADD CONSTRAINT 
fk_memberships_super_hero
FOREIGN KEY
(super_hero_id)
REFERENCES
super_hero(super_hero_id);

USE SuperHeroSightings_TEST;
ALTER TABLE memberships
ADD CONSTRAINT 
fk_memberships_organization
FOREIGN KEY
(organization_id)
REFERENCES
organization(organization_id);