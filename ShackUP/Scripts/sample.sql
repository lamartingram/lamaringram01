IF EXISTS(SELECT * FROM INFORMATION_SCHEMA.ROUTINES
	WHERE ROUTINE_NAME = 'StatesSelectAll')
	DROP PROCEDURE StatesSelectAll
	GO

	CREATE Procedure StatesSelectAll AS
	BEGIN 

	Select StateId, StateName
	FROM States

	END
	GO

	IF EXISTS(SELECT * FROM INFORMATION_SCHEMA.ROUTINES
	WHERE ROUTINE_NAME = 'BathroomTypeSelectAll')
	DROP PROCEDURE BathroomTypeSelectAll
	GO

	CREATE PROCEDURE BathroomTypeSelectAll AS 
	Begin 
	SELECT BathroomTypeId, BathroomTypeName
	FROM BathroomTypes
	ORDER BY BathroomTypeName
	END

	Go

	IF EXISTS(SELECT * FROM INFORMATION_SCHEMA.ROUTINES
	WHERE ROUTINE_NAME = 'ListingInsert') 
	DROP PROCEDURE ListingInsert
	GO

	CREATE PROCEDURE ListingInsert (
	@ListingId int output,
	@UserId nvarchar(128),
	@StateId char(2),
	@BathroomTypeId int,
	@Nickname nvarchar(50),
	@City nvarchar(50),
	@Rate decimal(7,2),
	@SquareFootage decimal(7,2),
	@HasElectric bit,
	@HasHeat bit,
	@ImageFileName varchar(50)
	) AS
	Begin 
	Insert into Listings(UserId, StateId, BathroomTypeId, NickName, City, Rate, SquareFootage, HasElectric, HasHeat, ImageFileName)
	Values (@UserId, @StateId, @BathroomTypeId, @Nickname, @City, @Rate, @SquareFootage, @HasElectric, @HasHeat, @ImageFileName);

	SET @ListingId = SCOPE_IDENTITY();

	END
	GO

	IF EXISTS(SELECT * FROM INFORMATION_SCHEMA.ROUTINES
	WHERE ROUTINE_NAME = 'ListingUpdate')
	DROP PROCEDURE ListingUpdate
	Go

	CREATE PROCEDURE ListingUpdate (
	@ListingId int ,
	@UserId nvarchar(128),
	@StateId char(2),
	@BathroomTypeId int,
	@Nickname nvarchar(50),
	@City nvarchar(50),
	@Rate decimal(7,2),
	@SquareFootage decimal(7,2),
	@HasElectric bit,
	@HasHeat bit,
	@ImageFileName varchar(50)
	) AS
	Begin 
	Update Listings SET
	UserId = @UserId , 
	StateId = @StateId,
	BathroomTypeId = @BathroomTypeId,
	NickName = @Nickname,
	City = @City,
    Rate = @Rate,
    SquareFootage = @SquareFootage, 
	HasElectric = @HasElectric, 
    HasHeat = @HasHeat, 
	ImageFileName = @ImageFileName	
WHERE ListingId = @ListingId
	END

	GO
		IF EXISTS(SELECT * FROM INFORMATION_SCHEMA.ROUTINES
	WHERE ROUTINE_NAME = 'ListingDelete')
	DROP PROCEDURE ListingDelete
	GO

	CREATE PROCEDURE ListingDelete (
	@ListingId int 
	)AS
	Begin
	Begin transaction
	delete from Contacts where ListingId = @ListingId;
	delete from Favorites where ListingId = @ListingId;
	delete from Listings where ListingId = @ListingId;

	Commit transaction
	End 
	Go

	
		IF EXISTS(SELECT * FROM INFORMATION_SCHEMA.ROUTINES
	WHERE ROUTINE_NAME = 'ListingSelect')
	DROP PROCEDURE ListingSelect
	GO

	CREATE PROCEDURE ListingSelect (

	@ListingId int 

	)AS
	Begin
	Select ListingId, UserId, StateId, BathroomTypeId, NickName, City, Rate, SquareFootage,
	HasElectric, HasHeat, ImageFileName
	From Listings
	Where ListingId = @ListingId

	End 
	Go

		IF EXISTS(SELECT * FROM INFORMATION_SCHEMA.ROUTINES
	WHERE ROUTINE_NAME = 'ListingSelectRecent')
	DROP PROCEDURE ListingSelectRecent
	GO

	CREATE PROCEDURE ListingSelectRecent As
	Begin
	Select Top 5 ListingId, UserId, Rate, City, StateId, ImageFileName
	From Listings
	Order by createDate Desc

	End 
	Go