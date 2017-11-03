use Hotel;
insert into Customer (FirstName, LastName, Phone, Email)
values
('Lamar', 'Ingram', '996-2236', 'lamartingram@gmail.com'),
('Latanya', 'Hughes', '271-8526', 'lhughes@yahoo.com'),
('Laquan', 'Ingram', '502-333' , 'laquan@gmail.com'),
('famliy', 'Guy', '888-888', 'familyguy@gmail.com'),
('Peter', 'Griffin', '999-999', 'petergriffin@yhaoo.com'),
('boon', 'docks', '111-111', 'boondocks@gmail.com'),
('riley', 'freeman', '333-333', 'rileyfreeman@yahoo.com'),
('south', 'park', '555-555', 'southpark@gmail.com'); 


insert into Reservation
(CustomerID, StartDate, EndDate, Paid)
values
(1, '2017/02/22', '2017/02/28', 0), (2, '2017/02/22', '2017/02/23', 0), (3, '2017/04/06', '2017/04/08', 0),
(4, '2017/05/23', '2017/05/24', 0), (5, '2017/04/10', '2017/04/12',0) , (6, '2017/07/01', '2017/07/05',0);

insert into Guest
(GuestName, GuestAge, ReservationID)
values
('Lamar Ingram', 19, 1), ('Lauren Ingram', 9, 1), ('Laquan Ingram', 15, 1), ('Lawrence Ingram', 60, 1),
('Sally Graves', 43, 2), ('Jonetta Johnson', 58, 2), ('Marcuie Johnson', 1, 2), ('Recce Taylor', 36, 2),
('Tim Sparks', 38, 3), ('Evel Knievel', 10, 3), ('Donald Trump', 80, 3), ('kaepernick', 28, 3);

insert into RoomTransaction
(RoomID, DiscountID, ReservationID)
values
(1, 1, 1), (10,1,1),
(9, 1, 2), (8, 1, 2),
(2, 3, 3);

insert into BillDetails (BillDetailsID, TotalRoomTransaction, TotalServiceTransaction) values (1, 1, 1);
insert into BillDetails (BillDetailsID, TotalRoomTransaction, TotalServiceTransaction) values (2, 3, 8);
insert into BillDetails (BillDetailsID, TotalRoomTransaction, TotalServiceTransaction) values (3, 7, 4);
insert into BillDetails (BillDetailsID, TotalRoomTransaction, TotalServiceTransaction) values (4, 8, 6);
insert into BillDetails (BillDetailsID, TotalRoomTransaction, TotalServiceTransaction) values (5, 3, 3);
insert into BillDetails (BillDetailsID, TotalRoomTransaction, TotalServiceTransaction) values (6, 8, 0);
insert into BillDetails (BillDetailsID, TotalRoomTransaction, TotalServiceTransaction) values (7, 2, 5);
insert into BillDetails (BillDetailsID, TotalRoomTransaction, TotalServiceTransaction) values (8, 3, 5);
insert into BillDetails (BillDetailsID, TotalRoomTransaction, TotalServiceTransaction) values (9, 7, 2);
insert into BillDetails (BillDetailsID, TotalRoomTransaction, TotalServiceTransaction) values (10, 8, 1);
insert into BillDetails (BillDetailsID, TotalRoomTransaction, TotalServiceTransaction) values (11, 4, 1);
insert into BillDetails (BillDetailsID, TotalRoomTransaction, TotalServiceTransaction) values (12, 2, 6);
insert into BillDetails (BillDetailsID, TotalRoomTransaction, TotalServiceTransaction) values (13, 7, 2);
insert into BillDetails (BillDetailsID, TotalRoomTransaction, TotalServiceTransaction) values (14, 6, 2);
insert into BillDetails (BillDetailsID, TotalRoomTransaction, TotalServiceTransaction) values (15, 8, 7);
insert into BillDetails (BillDetailsID, TotalRoomTransaction, TotalServiceTransaction) values (16, 4, 8);
insert into BillDetails (BillDetailsID, TotalRoomTransaction, TotalServiceTransaction) values (17, 8, 5);
insert into BillDetails (BillDetailsID, TotalRoomTransaction, TotalServiceTransaction) values (18, 10, 1);
insert into BillDetails (BillDetailsID, TotalRoomTransaction, TotalServiceTransaction) values (19, 7, 3);
insert into BillDetails (BillDetailsID, TotalRoomTransaction, TotalServiceTransaction) values (20, 7, 1);
insert into BillDetails (BillDetailsID, TotalRoomTransaction, TotalServiceTransaction) values (21, 4, 7);
insert into BillDetails (BillDetailsID, TotalRoomTransaction, TotalServiceTransaction) values (22, 0, 1);
insert into BillDetails (BillDetailsID, TotalRoomTransaction, TotalServiceTransaction) values (23, 8, 0);
insert into BillDetails (BillDetailsID, TotalRoomTransaction, TotalServiceTransaction) values (24, 5, 10);
insert into BillDetails (BillDetailsID, TotalRoomTransaction, TotalServiceTransaction) values (25, 1, 10);

insert into Bill (BillID, TotalSale, Totaltax, GrandTotal, BillDetailsID) values (1, 10, 89, 352, 1);
insert into Bill (BillID, TotalSale, Totaltax, GrandTotal, BillDetailsID) values (2, 6, 50, 745, 2);
insert into Bill (BillID, TotalSale, Totaltax, GrandTotal, BillDetailsID) values (3, 6, 18, 864, 3);
insert into Bill (BillID, TotalSale, Totaltax, GrandTotal, BillDetailsID) values (4, 7, 72, 314, 4);
insert into Bill (BillID, TotalSale, Totaltax, GrandTotal, BillDetailsID) values (5, 7, 7, 458, 5);
insert into Bill (BillID, TotalSale, Totaltax, GrandTotal, BillDetailsID) values (6, 8, 85, 882, 6);
insert into Bill (BillID, TotalSale, Totaltax, GrandTotal, BillDetailsID) values (7, 8, 34, 471, 7);
insert into Bill (BillID, TotalSale, Totaltax, GrandTotal, BillDetailsID) values (8, 5, 13, 317, 8);
insert into Bill (BillID, TotalSale, Totaltax, GrandTotal, BillDetailsID) values (9, 6, 79, 51, 9);
insert into Bill (BillID, TotalSale, Totaltax, GrandTotal, BillDetailsID) values (10, 10, 26, 845, 10);
insert into Bill (BillID, TotalSale, Totaltax, GrandTotal, BillDetailsID) values (11, 7, 44, 200, 11);
insert into Bill (BillID, TotalSale, Totaltax, GrandTotal, BillDetailsID) values (12, 9, 34, 672, 12);
insert into Bill (BillID, TotalSale, Totaltax, GrandTotal, BillDetailsID) values (13, 7, 50, 731, 13);
insert into Bill (BillID, TotalSale, Totaltax, GrandTotal, BillDetailsID) values (14, 10, 27, 955, 14);
insert into Bill (BillID, TotalSale, Totaltax, GrandTotal, BillDetailsID) values (15, 5, 52, 143, 15);
insert into Bill (BillID, TotalSale, Totaltax, GrandTotal, BillDetailsID) values (16, 9, 88, 55, 16);
insert into Bill (BillID, TotalSale, Totaltax, GrandTotal, BillDetailsID) values (17, 10, 85, 249, 17);
insert into Bill (BillID, TotalSale, Totaltax, GrandTotal, BillDetailsID) values (18, 8, 56, 791, 18);
insert into Bill (BillID, TotalSale, Totaltax, GrandTotal, BillDetailsID) values (19, 8, 76, 620, 19);
insert into Bill (BillID, TotalSale, Totaltax, GrandTotal, BillDetailsID) values (20, 6, 31, 790, 20);
insert into Bill (BillID, TotalSale, Totaltax, GrandTotal, BillDetailsID) values (21, 8, 30, 279, 21);
insert into Bill (BillID, TotalSale, Totaltax, GrandTotal, BillDetailsID) values (22, 7, 37, 338, 22);
insert into Bill (BillID, TotalSale, Totaltax, GrandTotal, BillDetailsID) values (23, 8, 45, 631, 23);
insert into Bill (BillID, TotalSale, Totaltax, GrandTotal, BillDetailsID) values (24, 9, 82, 288, 24);
insert into Bill (BillID, TotalSale, Totaltax, GrandTotal, BillDetailsID) values (25, 7, 40, 733, 25);



