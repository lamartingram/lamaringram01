Use Hotel;
insert into Ammenity (AmmenityName) values ('jaccuzi'), ('balcony'), ('Movie Screen Tv');

insert into Discount (DiscountName, DiscountAmount) values ('CustomerDiscount' , 0.85), ('MilitaryDiscount', 0.90), ('PeakTime', 1.5);

insert into RoomRates (RateAmount) values (200.00), (150.00), (80.00);

insert into Services (Name, Price) values ('movie', 7.00), ('room service', 12.00), ('parking', 5.00); 

insert into RoomType (Name, OccupancyLimit, RateID) values ('penthouse', 6, 1), ('condo', 4, 2), ('apartment-style', 2, 3);

insert into Room (RoomNumber, FloorNumber, RoomTypeID)
values
(101, 1, 1), (102, 1, 1) , (103, 1, 1), (201, 2, 2), (202, 2, 2), (203, 2, 2), (301, 3, 3), (302, 3, 3) ,(303, 3, 3), (304, 3, 3);

insert into RoomAmmenity (RoomID, AmmenityID, AmmenityCount)
values
(1,1,1),(1,2,1),(1,3,1),(2,1,1),(2,2,1),(2,3,1),(3,1,1),(3,2,1),(3,3,1),
(4,2,1), (4,3,1), (5,2,1), (5,3,1), (6,2,1), (6,3,1), 
(7,3,1), (8,3,1), (9,3,1), (10,3,1),(10,1,1);
