drop database if exists Hotel;
	
create database Hotel;
use Hotel;

create table Customer
(CustomerID int(11) not null auto_increment,
FirstName varchar(30) not null,
LastName varchar(30) not null,
Phone varchar(30) not null,
Email varchar(30) not null,
primary key(CustomerID));

create table Ammenity
(AmmenityID int(11) not null auto_increment,
AmmenityName varchar(30) not null,
primary key(AmmenityID));

create table RoomRates
(RateID int(11) not null auto_increment,
RateAmount decimal not null,
primary key(RateID));

create table Services
(ServiceID int(11) not null auto_increment,
Name varchar(30) not null,
Price Decimal not null,
primary key(ServiceID));

create table Discount
(DiscountID int(11) not null auto_increment,
DiscountName varchar(30) not null,
DiscountAmount decimal not null,
primary key(DiscountID));
 	 	 		
create table BillDetails
(BillDetailsID int(11) not null auto_increment,
TotalRoomTransaction decimal not null,
TotalServiceTransaction decimal not null,
primary key(BillDetailsID));

create table Bill
(BillID int(11) not null auto_increment,
TotalSale decimal not null,
Totaltax decimal not null,
GrandTotal decimal not null,
BillDetailsID int(11) not null,
primary key(BillID));

alter table Bill
add constraint fk_Bill_BillDetails
foreign key(BillDetailsID) references BillDetails(BillDetailsID);
 	 	 		
create table Reservation
(ReservationID int(11) not null auto_increment,
CustomerID int(11) not null,
StartDate date not null,
EndDate date not null,
BillID int(11),  
Paid bool not null,
primary key(ReservationID));

alter table Reservation
add constraint fk_Reservation_Customer
foreign key(CustomerID) references Customer(CustomerID);

alter table Reservation
add constraint fk_Reservation_Bill
foreign key(BillID) references Bill(BillID);

create table RoomType
(RoomTypeID int(11) not null auto_increment,
Name varchar(30) not null,
OccupancyLimit int(11) not null,
RateID int(11) not null,
primary key(RoomTypeID));


alter table RoomType
add constraint fk_RoomType_Rate
foreign key(RateID) references RoomRates(RateID);

create table Guest
(GuestID int(11) not null auto_increment,
GuestName varchar(40) not null,
GuestAge int(11) not null,
ReservationID int(11) not null,
primary key(GuestID));

alter table Guest
add constraint fk_Guest_Reservation
foreign key(ReservationID) references Reservation(ReservationID);

create table Room
(RoomID int(11) not null auto_increment,
RoomNumber int(11) not null,
FloorNumber int(11) not null,
RoomTypeID int(11) not null,
primary key(RoomID));
 	 	 	
alter table Room
add constraint fk_Room_RoomType
foreign key(RoomTypeID) references RoomType(RoomTypeID);

create table ServiceTransaction
(TransactionID int(11) not null auto_increment,
ServiceID int(11) not null,
ReservationID int(11) not null,
TransactionDate date not null,
primary key(TransactionID));

alter table ServiceTransaction
add constraint fk_ServiceTransactions_Service
foreign key(ServiceID) references Services(ServiceID);

alter table ServiceTransaction
add constraint fk_ServiceTransactions_Reservation
foreign key(ReservationID) references Reservation(ReservationID);
 	 	 		
create table RoomAmmenity
(RoomAmmenityID int(11) not null auto_increment,
RoomID int(11) not null,
AmmenityID int(11) not null,
AmmenityCount int(11) not null,
primary key(RoomAmmenityID));

alter table RoomAmmenity
add constraint fk_RoomAmmenity_Room
foreign key(RoomID) references Room(RoomID);

alter table RoomAmmenity
add constraint fk_RoomAmmenity_Ammenity
foreign key(AmmenityID) references Ammenity(AmmenityID);

create table RoomTransaction
(RoomTransactionID int(11) not null auto_increment,
RoomID int(11) not null,
DiscountID int(11) not null,
ReservationID int(11) not null,
primary key(RoomTransactionID));

alter table RoomTransaction
add constraint fk_RoomTransaction_Room
foreign key(RoomID) references Room(RoomID);

alter table RoomTransaction
add constraint fk_RoomTransaction_Discount
foreign key(DiscountID) references Discount(DiscountID);
 	 	 		
alter table RoomTransaction
add constraint fk_RoomTransaction_Reservation
foreign key(ReservationID) references Reservation(ReservationID);