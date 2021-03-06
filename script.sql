DELETE FROM PocketAccount;
DELETE FROM RateTrack;
DELETE FROM CoOwner;
DELETE FROM Transaction;
DELETE FROM Account;
DELETE FROM Customer;

INSERT INTO Customer VALUES (361721022, 'Alfred Hitchcock', '6667 El Colegio #40', 'bdfh');
INSERT INTO Customer VALUES (231403227, 'Billy Clinton', '5777 Hollister', 'bhlp');
INSERT INTO Customer VALUES (412231856, 'Cindy Laugher', '7000 Hollister', 'fnlh');
INSERT INTO Customer VALUES (207843218,	'David Copperfill', '1357 State St', 'pjpd');
INSERT INTO Customer VALUES (122219876, 'Elizabeth Sailor', '4321 State St', 'fpjl');
INSERT INTO Customer VALUES (401605312, 'Fatal Castro', '3756 La Cumbre Plaza', 'pbrf');
INSERT INTO Customer VALUES (201674933, 'George Brush', '5346 Foothill Avenue', 'rpdf');
INSERT INTO Customer VALUES (212431965, 'Hurryson Ford', '678 State St', 'fjfd');
INSERT INTO Customer VALUES (322175130, 'Ivan Lendme', '1235 Johnson Dr','phnb');
INSERT INTO Customer VALUES (344151573, 'Joe Pepsi', '3210 State St','flrd');
INSERT INTO Customer VALUES (209378521, 'Kevin Coster', 'Santa Cruz #3579','hljr');
INSERT INTO Customer VALUES (212116070, 'Li Kung', '2 Peoples Rd Beijing','rbnf');
INSERT INTO Customer VALUES (188212217, 'Magic Jordon', '3852 Court Rd', 'nfjb');
INSERT INTO Customer VALUES (203491209, 'Nam-hoi Chung', '1997 Peoples St HK', 'jfh`');
INSERT INTO Customer VALUES (210389768, 'Olive Stoner', '6689 El Colegio #151', 'phjd');
INSERT INTO Customer VALUES (400651982, 'Pit Wilson', '911 State St', 'bpdb');




INSERT INTO Transaction (transaction_id,transDate ,moneyTrans,transType ,incrAcctID ,decrAcctID ),VALUES (15, '3-2-2011', 8800, 0, 17431, null  )


INSERT INTO Account VALUES (17431,null,1,9289,344151573,1);
INSERT INTO Account VALUES (54321,null,1,1800,212431965,1);
INSERT INTO Account VALUES (12121,null,1,950,207843218,1);
INSERT INTO Account VALUES (41725,null,0,10000,201674933,1.055);
INSERT INTO Account VALUES (76543,null,0,6000,212116070,1.055);
INSERT INTO Account VALUES (93156,null,0,80000,209378521,1.055);
INSERT INTO Account VALUES (43942,null,2,3280,361721022,1.075);
INSERT INTO Account VALUES (29107,null,2,299650,209378521,1.075);
INSERT INTO Account VALUES (19023,null,2,3200,412231856,1.075); 
INSERT INTO Account VALUES (32156,null,2,5000,188212217,1.075);
INSERT INTO Account VALUES (53027,null,3,55,207843218,1.055);
INSERT INTO Account VALUES (43947,null,3,35, 212116070,1.055);
INSERT INTO Account VALUES (60413,null,3,5, 361721022,1.055);
INSERT INTO Account VALUES (67521,null,3,140, 209378521,1.055);

//transaction types
//0 - deposit
//1 - top up
//2 - withdrawal
//3 -  purchase
//4 - transfer
//5 - collect
//6 - pay friend
//7 - wire
//8 - write-check
//9 - accrue interest
//10 - fees

INSERT INTO Transaction VALUES(1, TO_DATE('3-3-2011', 'MM-DD-YYYY'), 200, 0, 17431, null);
INSERT INTO Transaction VALUES(2, TO_DATE('3-3-2011', 'MM-DD-YYYY'), 21000, 0 ,54321, null);
INSERT INTO Transaction VALUES(3, TO_DATE('3-3-2011', 'MM-DD-YYYY'),1200, 0,12121, null);
INSERT INTO Transaction VALUES(4, TO_DATE('3-3-2011', 'MM-DD-YYYY') ,15000, 0,41725, null);
INSERT INTO Transaction VALUES(5, TO_DATE('3-3-2011', 'MM-DD-YYYY') ,2000000, 0,93156, null);
INSERT INTO Transaction VALUES(6, TO_DATE('3-4-2011', 'MM-DD-YYYY') ,50, 1,53027, 12121);
INSERT INTO Transaction VALUES(7, TO_DATE('3-4-2011', 'MM-DD-YYYY') ,1289, 0,43942, null);
INSERT INTO Transaction VALUES(8, TO_DATE('3-4-2011', 'MM-DD-YYYY') ,34000, 0,29107, null);
INSERT INTO Transaction VALUES(9, TO_DATE('3-5-2011', 'MM-DD-YYYY') ,2300, 0,19023, null);
INSERT INTO Transaction VALUES(10,TO_DATE('3-5-2011', 'MM-DD-YYYY') ,20, 1,60413, 43942);
INSERT INTO Transaction VALUES(11,TO_DATE('3-5-2011', 'MM-DD-YYYY') ,1000, 0,32156, null);
INSERT INTO Transaction VALUES(12,TO_DATE('3-5-2011', 'MM-DD-YYYY') ,8456, 0,76543, null);
INSERT INTO Transaction VALUES(13,TO_DATE('3-5-2011', 'MM-DD-YYYY') ,30, 1,43947, 29107);
INSERT INTO Transaction VALUES(14,TO_DATE('3-6-2011', 'MM-DD-YYYY') ,100, 1,67521, 19023);


//Other transactions
INSERT INTO Transaction VALUES(15, TO_DATE('3-2-2011', 'MM-DD-YYYY'), 8800, 0, 17431, null);
INSERT INTO Transaction VALUES(16, TO_DATE('3-3-2011', 'MM-DD-YYYY'), 3000, 2, null, 54321);
INSERT INTO Transaction VALUES(17, TO_DATE('3-5-2011', 'MM-DD-YYYY'), 2000, 2, null, 76543);
INSERT INTO Transaction VALUES(18, TO_DATE('3-5-2011', 'MM-DD-YYYY'), 5, 3, null, 53027);
INSERT INTO Transaction VALUES(19, TO_DATE('3-6-2011', 'MM-DD-YYYY'), 1000000, 2, null, 93156);
INSERT INTO Transaction VALUES(20 ,TO_DATE('3-6-2011', 'MM-DD-YYYY'), 950000, 8, null, 93156);
INSERT INTO Transaction VALUES(21, TO_DATE('3-6-2011', 'MM-DD-YYYY'), 4000, 2, null, 29107);
INSERT INTO Transaction VALUES(22, TO_DATE('3-6-2011', 'MM-DD-YYYY'), 10, 5, 29107, 43947);
INSERT INTO Transaction VALUES(23, TO_DATE('3-6-2011', 'MM-DD-YYYY'), 30, 1, 43947, 29107);
INSERT INTO Transaction VALUES(24, TO_DATE('3-7-2011', 'MM-DD-YYYY'), 289, 1, 17431, 43942);
INSERT INTO Transaction VALUES(25, TO_DATE('3-7-2011', 'MM-DD-YYYY'), 289, 2, 43942, null);
INSERT INTO Transaction VALUES(26, TO_DATE('3-8-2011', 'MM-DD-YYYY'), 10, 6, 67521, 60413);
INSERT INTO Transaction VALUES(27, TO_DATE('3-8-2011', 'MM-DD-YYYY'), 50000, 0, 93156, null);
INSERT INTO Transaction VALUES(28, TO_DATE('3-8-2011', 'MM-DD-YYYY'), 200, 8, null, 12121);
INSERT INTO Transaction VALUES(29, TO_DATE('3-8-2011', 'MM-DD-YYYY'), 1000, 4, 19023, 41725);
INSERT INTO Transaction VALUES(30, TO_DATE('3-9-2011', 'MM-DD-YYYY'), 4000, 7, 32156, 41725);
INSERT INTO Transaction VALUES(31, TO_DATE('3-9-2011', 'MM-DD-YYYY'), 10, 6, 60413, 53027);
INSERT INTO Transaction VALUES(32, TO_DATE('3-10-2011', 'MM-DD-YYYY'), 15, 3, null, 60413);
INSERT INTO Transaction VALUES(33, TO_DATE('3-12-2011', 'MM-DD-YYYY'), 20000, 2, null, 93156);
INSERT INTO Transaction VALUES(34, TO_DATE('3-12-2011', 'MM-DD-YYYY'), 456, 8, null, 76543);
INSERT INTO Transaction VALUES(35, TO_DATE('3-12-2011', 'MM-DD-YYYY'), 50, 1, 67521, 19023);
INSERT INTO Transaction VALUES(36, TO_DATE('3-14-2011', 'MM-DD-YYYY'), 20, 4, 53027, 67521);
INSERT INTO Transaction VALUES(37, TO_DATE('3-14-2011', 'MM-DD-YYYY'), 15, 5, 29107, 43947);




INSERT INTO PocketAccount VALUES (53027,12121,null);
INSERT INTO PocketAccount VALUES (60413,43942,null);
INSERT INTO PocketAccount VALUES (43947,29107,null);
INSERT INTO PocketAccount VALUES (67521,19023,null);













INSERT INTO CoOwner VALUES (412231856, 17431);
INSERT INTO CoOwner VALUES (322175130, 17431);

INSERT INTO CoOwner VALUES (412231856, 54321);
INSERT INTO CoOwner VALUES (122219876, 54321);
INSERT INTO CoOwner VALUES (203491209, 54321);

INSERT INTO CoOwner VALUES (401605312, 41725);
INSERT INTO CoOwner VALUES (231403227, 41725);

INSERT INTO CoOwner VALUES (188212217,76543);

INSERT INTO CoOwner VALUES (188212217, 93156);
INSERT INTO CoOwner VALUES (210389768, 93156);
INSERT INTO CoOwner VALUES (122219876, 93156);
INSERT INTO CoOwner VALUES (203491209, 93156);

INSERT INTO CoOwner VALUES (400651982,43942);
INSERT INTO CoOwner VALUES (212431965,43942);
INSERT INTO CoOwner VALUES (322175130,43942);

INSERT INTO CoOwner VALUES (212116070,29107);
INSERT INTO CoOwner VALUES (210389768,29107);

INSERT INTO CoOwner VALUES (201674933,19023);
INSERT INTO CoOwner VALUES (401605312,19023);

INSERT INTO CoOwner VALUES (207843218,32156);
INSERT INTO CoOwner VALUES (122219876, 32156);
INSERT INTO CoOwner VALUES (344151573,32156);
INSERT INTO CoOwner VALUES (203491209,32156);
INSERT INTO CoOwner VALUES (210389768,32156);

INSERT INTO CoOwner VALUES (210389768,43947);

INSERT INTO CoOwner VALUES (400651982, 60413);
INSERT INTO CoOwner VALUES (122219876, 60413);
INSERT INTO CoOwner VALUES (231403227, 60413);

INSERT INTO CoOwner VALUES (401605312, 67521);
INSERT INTO CoOwner VALUES (212431965, 67521);




