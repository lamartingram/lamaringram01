IF EXISTS(SELECT * FROM INFORMATION_SCHEMA.ROUTINES
	WHERE ROUTINE_NAME = 'DbReset')
	DROP PROCEDURE DbReset
	GO

	CREATE Procedure DbReset AS

	BEGIN 

	Delete FROM Listings;
	DELETE FROM States;
	Delete FROM BathroomTypes;
	DELETE FROM AspNetUsers Where id= '00000000-0000-0000-0000-000000000000'

	DBCC Checkident ('Listings', RESEED, 1)

	INSERT INTO States (StateId, StateName)
	Values('OH' , 'Ohio'),
	('KY', 'Kentucky'),
	('MN', 'Minnestoa');

	Set IDENTITY_INSERT BathroomTypes ON;

	Insert INTO BathroomTypes(BathroomTypeID, BathroomTypeName)
	Values (1, 'Indoor'),
	(2, 'Outdoor'),
	(3,'None')

	SET IDENTITY_INSERT BathroomTypes OFF;


	INSERT INTO AspNetUsers(Id, EmailConfirmed, PhoneNumberConfirmed, Email, StateId, TwoFactorEnabled, LockoutEnabled, AccessFailedCount, UserName)
	Values ('00000000-0000-0000-0000-000000000000', 0, 0, 'test@test.com', 'OH', 0, 0, 0, 'test');

	SET IDENTITY_INSERT Listings ON;

	Insert INTO Listings(ListingId, UserId, StateId, BathroomTypeId, NickName, City, Rate, SquareFootage, HasElectric, HasHeat, ImageFileName)
	Values( 1,'00000000-0000-0000-0000-000000000000', 'OH', 3, 'Test shack 1', 'Cleveland', 120, 400, 0, 1,'placeholder.png'),
	( 2,'00000000-0000-0000-0000-000000000000', 'OH', 3, 'Test shack 2', 'Cleveland', 110, 410, 0, 1,'placeholder.png'),
	( 3,'00000000-0000-0000-0000-000000000000', 'OH', 3, 'Test shack 3', 'Cleveland', 120, 420, 0, 1,'placeholder.png'),
	( 4,'00000000-0000-0000-0000-000000000000', 'OH', 3, 'Test shack 4', 'Cleveland', 130, 430, 0, 1,'placeholder.png'),
	( 5,'00000000-0000-0000-0000-000000000000', 'OH', 3, 'Test shack 5', 'Cleveland', 140, 440, 0, 1,'placeholder.png'),
	( 6,'00000000-0000-0000-0000-000000000000', 'OH', 3, 'Test shack 6', 'Cleveland', 150, 450, 0, 1,'placeholder.png');

	SET IDENTITY_INSERT Listings OFF; 

	END

