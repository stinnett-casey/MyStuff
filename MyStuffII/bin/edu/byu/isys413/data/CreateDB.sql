-- Note that comment lines need to end with a semicolon for CreateDB.java to work;

-- The primary keys (id) should really be VARCHAR(40), not VARCHAR(40), but;
-- to make life easier in testing, I've placed them as VARCHAR(40) for now;

-- First drop everything (order matters here for foreign keys!);

DROP TABLE commission;
DROP TABLE debitcredit;
DROP TABLE journalentry;
DROP TABLE sale;
DROP TABLE fee;
DROP TABLE rental;
DROP TABLE revenuesource;
DROP TABLE txn; 
DROP TABLE generalledger;
DROP TABLE membership;
DROP TABLE customer;
DROP TABLE storeproduct;
DROP TABLE forrent;
DROP TABLE forsale;
DROP TABLE physicalproduct;
DROP TABLE conceptualrental;
DROP TABLE conceptualproduct;
DROP TABLE product;
ALTER TABLE store DROP CONSTRAINT fk_emp;
ALTER TABLE employee DROP CONSTRAINT fk_store;
DROP TABLE store;
DROP TABLE employee;
DROP TABLE payment;
DROP TABLE businessobject;



-- BUSINESSOBJECT TABLE (everything extends this);
CREATE TABLE businessobject (
  id           VARCHAR(40) PRIMARY KEY,
  botype       VARCHAR(250)
);

--STORE TABLE;
CREATE TABLE store (
  id           	VARCHAR(40) PRIMARY KEY REFERENCES businessobject(id),
  location     	VARCHAR(50),
  phone      	VARCHAR(50),
  address       VARCHAR(50),
  city        	VARCHAR(50),
  state        	VARCHAR(50),
  zip       	VARCHAR(50),
  managerid		VARCHAR(40),
  salestaxrate	FLOAT,
  subnetid		VARCHAR(40)
);
INSERT INTO businessobject(id, botype) VALUES ('store1', 'edu.byu.isys413.data.Store');
INSERT INTO store VALUES ('store1', 'Provo', '801-642-7789', '1125 N 15 E', 'Provo', 'UT', '84606', 'emp1', .06, 'E0-94-67-05-DA-9C');
INSERT INTO businessobject(id, botype) VALUES ('store2', 'edu.byu.isys413.data.Store');
INSERT INTO store VALUES ('store2', 'Ogden', '801-562-1564', '14 N 200 E', 'Ogden', 'UT', '84619', 'emp2', .058, 'F0-DE-F1-F9-34-39');




-- EMPLOYEE TABLE;
CREATE TABLE employee (
  id           	VARCHAR(40) PRIMARY KEY REFERENCES businessobject(id),
  first_name    VARCHAR(50),
  middle_name	VARCHAR(50),
  last_name     VARCHAR(50),
  hire_date		DATE,
  salary		NUMERIC(8,2),
  phone        	VARCHAR(100),
  username		VARCHAR(100),
  manager		BOOLEAN,
  subnetID		VARCHAR(40),
  storeid		VARCHAR(40)
);
INSERT INTO businessobject(id, botype) VALUES ('emp1', 'edu.byu.isys413.data.Employee');
INSERT INTO employee VALUES ('emp1', 'Casey', 'John', 'Stinnett', '2012-10-5', 75000.00, '928-243-8696', 'cstinnet', true, '4435', 'store1');
INSERT INTO businessobject(id, botype) VALUES ('emp2', 'edu.byu.isys413.data.Employee');
INSERT INTO employee VALUES ('emp2', 'Todd', 'Q', 'Wilson', '2012-1-19', 99000.00, '928-243-8676', 'twilson5', false, '98-3', 'store2');



--PRODUCT TABLE;
CREATE TABLE product (
	id			VARCHAR(40) PRIMARY KEY REFERENCES businessobject(id),
	price		FLOAT,
	physical	BOOLEAN
);
INSERT INTO businessobject(id, botype) VALUES ('prod1', 'edu.byu.isys413.data.Product');
INSERT INTO product VALUES ('prod1', 23.25, true);
INSERT INTO businessobject(id, botype) VALUES ('prod2', 'edu.byu.isys413.data.Product');
INSERT INTO product VALUES ('prod2', 225.47, true);
INSERT INTO businessobject(id, botype) VALUES ('prod3', 'edu.byu.isys413.data.Product');
INSERT INTO product VALUES ('prod3', 555.55, false);
INSERT INTO businessobject(id, botype) VALUES ('prod4', 'edu.byu.isys413.data.Product');
INSERT INTO product VALUES ('prod4', 666.66, false);


--CONCEPTUALPRODUCT TABLE;
CREATE TABLE conceptualproduct(
	id					VARCHAR(40) PRIMARY KEY REFERENCES product(id),
	product_name		VARCHAR(50),
	description			VARCHAR(150),
	manufacturer		VARCHAR(50),
	avg_cost			FLOAT,
	commissionrate		FLOAT,
	SKU					VARCHAR(40)
);
INSERT INTO businessobject(id, botype) VALUES ('concepprod1', 'edu.byu.isys413.data.ConceptualProduct');
INSERT INTO product VALUES ('concepprod1', 899.99, true);
INSERT INTO conceptualproduct VALUES ('concepprod1', 'FujiFilm Z570', 'This is a nice camera. Really.', 'Fuji', 881.36, .031, '124a');

INSERT INTO businessobject(id, botype) VALUES ('concepprod2', 'edu.byu.isys413.data.ConceptualProduct');
INSERT INTO product VALUES ('concepprod2', 699.99, true);
INSERT INTO conceptualproduct VALUES ('concepprod2', 'Soni 76" TV', 'This is a nice TV. Really.', 'Soni', 699.99, .042, '321b');



--CONCEPTUAL RENTAL TABLE;
CREATE TABLE conceptualrental (
	id					VARCHAR(40) PRIMARY KEY REFERENCES conceptualproduct(id),
	price_per_day		FLOAT,
	replacement_price	FLOAT
);
INSERT INTO businessobject(id, botype) VALUES ('concepprod3', 'edu.byu.isys413.data.ConceptualProduct');
INSERT INTO product VALUES ('concepprod3', 259.99, true);
INSERT INTO conceptualproduct VALUES ('concepprod3', 'iTouch 64GB', 'This is a sweet up itouch', 'Jared', 258.00, .01, '654C');
INSERT INTO conceptualrental VALUES ('concepprod3', 12.50, 220.00);

INSERT INTO businessobject(id, botype) VALUES ('concepprod4', 'edu.byu.isys413.data.ConceptualProduct');
INSERT INTO product VALUES ('concepprod4', 1599.99, true);
INSERT INTO conceptualproduct VALUES ('concepprod4', 'Canon 560', 'Nothing more than a very, very expensive camera.', 'Canon', 1500.68, .042, '289d');
INSERT INTO conceptualrental VALUES ('concepprod4', 35.50, 1500.00);


--PHYSICAL PRODUCT TABLE;
CREATE TABLE physicalproduct (
	id					VARCHAR(40) PRIMARY KEY REFERENCES product(id),
	serialnumber		VARCHAR(40),
	shelflocation		VARCHAR(40),
	datepurchased		DATE,
	cost				FLOAT,
	status				VARCHAR(40),
	commissionrate		FLOAT,
	typekind			VARCHAR(40),
	conceptualproductid	VARCHAR(40) REFERENCES conceptualproduct(id)
);
--We don't need to insert any physical products here because they are inserted for for rent and for sale;

--FOR RENT TABLE;
CREATE TABLE forrent (
	id				VARCHAR(40) PRIMARY KEY REFERENCES physicalproduct(id),
	timesrented		INT
);
INSERT INTO businessobject(id, botype) VALUES ('forrent1', 'edu.byu.isys413.data.ForRent');
INSERT INTO product VALUES ('forrent1', 1599.99, TRUE);
INSERT INTO physicalproduct VALUES ('forrent1', 'r987654', 'a6s9', '2013-03-10', 1300.00, 'available', .056, 'rent', 'concepprod4');
INSERT INTO forrent VALUES ('forrent1', 2);
INSERT INTO businessobject(id, botype) VALUES ('forrent2', 'edu.byu.isys413.data.ForRent');
INSERT INTO product VALUES ('forrent2', 259.99, TRUE);
INSERT INTO physicalproduct VALUES ('forrent2', 'r123456', 'a6s9', '2013-03-10', 219.99, 'available', .031, 'rent', 'concepprod3');
INSERT INTO forrent VALUES ('forrent2', 5);

--FOR SALE TABLE;
CREATE TABLE forsale (
	id			VARCHAR(40) PRIMARY KEY REFERENCES physicalproduct(id),
	used		BOOLEAN
);
INSERT INTO businessobject(id, botype) VALUES ('forsale1', 'edu.byu.isys413.data.ForSale');
INSERT INTO product VALUES ('forsale1', 699.99, TRUE);
INSERT INTO physicalproduct VALUES ('forsale1', 's963852', 'a12s1', '2013-03-15', 599.99, 'available', .04, 'sale', 'concepprod2');
INSERT INTO forsale VALUES ('forsale1', FALSE);
INSERT INTO businessobject(id, botype) VALUES ('forsale2', 'edu.byu.isys413.data.ForSale');
INSERT INTO product VALUES ('forsale2', 899.99, TRUE);
INSERT INTO physicalproduct VALUES ('forsale2', 's258369', 'a1s7', '2013-03-2', 799.99, 'available', .049, 'sale', 'concepprod1');
INSERT INTO forsale VALUES ('forsale2', TRUE);



--STOREPRODUCT TABLE, ASSOCIATION CLASS BETWEEN STORE AND PRODUCT;
CREATE TABLE storeproduct(
	id					VARCHAR(40) PRIMARY KEY REFERENCES businessobject(id),
	storeid				VARCHAR(40) REFERENCES store(id),
	conceptualproductid	VARCHAR(40) REFERENCES conceptualproduct(id),
	quantity			VARCHAR(50),
	shelf_location		VARCHAR(50)	
);
INSERT INTO businessobject(id, botype) VALUES ('storeprod1', 'edu.byu.isys413.data.StoreProduct');
INSERT INTO storeproduct VALUES	('storeprod1', 'store1', 'concepprod1', '8', 'a7s2');
INSERT INTO businessobject(id, botype) VALUES ('storeprod2', 'edu.byu.isys413.data.StoreProduct');
INSERT INTO storeproduct VALUES	('storeprod2', 'store2', 'concepprod2', '50', 'a3s2');


--CUSTOMER TABLE;
CREATE TABLE customer (
	id				VARCHAR(40) PRIMARY KEY REFERENCES businessobject(id),
	first_name		VARCHAR(40),
	last_name		VARCHAR(40),
	phone			VARCHAR(40),
	email			VARCHAR(40),
	password		VARCHAR(40),
	address			VARCHAR(40),
	state			VARCHAR(40),
	city			VARCHAR(40),
	zip				VARCHAR(40),
	validationcode	VARCHAR(40),
	validated		BOOLEAN
);
INSERT INTO businessobject(id, botype) VALUES ('cust1', 'edu.byu.isys413.data.Customer');
INSERT INTO customer VALUES ('cust1', 'Jim', 'Server', '9282437784', 'mystuffcasey@gmail.com', 'iloveeggs', '1126 E 560 N', 'UT', 'Provo', '84606', '998877665544', true);
INSERT INTO businessobject(id, botype) VALUES ('cust2', 'edu.byu.isys413.data.Customer');
INSERT INTO customer VALUES ('cust2', 'Kevin', 'Timothy', '8018018011', 'gmail@yes.com', 'youeatfood', '11 E 92 N', 'UT', 'Ogden', '84701', '554466446655', false);
INSERT INTO businessobject(id, botype) VALUES ('cust3', 'edu.byu.isys413.data.Customer');
INSERT INTO customer VALUES ('cust3', 'Thomas', 'Stinnett', '1234567890', 'thomas@gmail.com', 'iamthomas', '87 E 560 N', 'UT', 'Logan', '84725', '1122334455', true);


--MEMBERSHIP TABLE;
CREATE TABLE membership (
	id				VARCHAR(40) PRIMARY KEY REFERENCES businessobject(id),
	ccnumber		VARCHAR(40),
	customerid		VARCHAR(40) REFERENCES customer(id)
);
INSERT INTO businessobject(id,botype) VALUES ('memb1', 'edu.byu.isys413.data.Membership');
INSERT INTO membership VALUES ('memb1', '4517998564712221', 'cust1');
INSERT INTO businessobject(id,botype) VALUES ('memb2', 'edu.byu.isys413.data.Membership');
INSERT INTO membership VALUES ('memb2', '4517998564712221', 'cust2');


--PAYMENT TABLE;
CREATE TABLE payment (
	id				VARCHAR(40) PRIMARY KEY REFERENCES businessobject(id),
	amount			FLOAT,
	change			FLOAT,
	typekind		VARCHAR(40)
);
INSERT INTO businessobject(id, botype) VALUES ('payment1', 'edu.byu.isys413.data.Payment');
INSERT INTO payment VALUES ('payment1', 149.5, 0.0, 'Credit');
INSERT INTO businessobject(id, botype) VALUES ('payment2', 'edu.byu.isys413.data.Payment');
INSERT INTO payment VALUES ('payment2', 14.34, 0.0, 'Cash');
INSERT INTO businessobject(id, botype) VALUES ('payment3', 'edu.byu.isys413.data.Payment');
INSERT INTO payment VALUES ('payment3', 1241.79, 0.0, 'Credit');
INSERT INTO businessobject(id, botype) VALUES ('payment4', 'edu.byu.isys413.data.Payment');
INSERT INTO payment VALUES ('payment4', 13.25, 0.0, 'Cash');



--COMMISSION TABLE;
CREATE TABLE commission (
	id					VARCHAR(40) PRIMARY KEY REFERENCES businessobject(id),
	employeeid			VARCHAR(40) REFERENCES employee(id),
	amount				FLOAT,
	commdate			DATE
);
INSERT INTO businessobject(id, botype) VALUES ('comm1', 'edu.byu.isys413.data.Commission');
INSERT INTO commission VALUES ('comm1', 'emp1', 52.21, '2012-12-16');
INSERT INTO businessobject(id, botype) VALUES ('comm2', 'edu.byu.isys413.data.Commission');
INSERT INTO commission VALUES ('comm2', 'emp1', 67.11, '2012-12-17');


--txn TABLE;
CREATE TABLE txn (
	id				VARCHAR(40) PRIMARY KEY REFERENCES businessobject(id),
	txndate			VARCHAR(40),
	subtotal		FLOAT,
	tax				FLOAT,
	total			FLOAT,
	employeeid		VARCHAR(40) REFERENCES employee(id),
	customerid		VARCHAR(40) REFERENCES customer(id),
	storeid			VARCHAR(40) REFERENCES store(id),
	paymentid		VARCHAR(40) REFERENCES payment(id),
	commissionid	VARCHAR(40) REFERENCES commission(id)
);
INSERT INTO businessobject(id, botype) VALUES ('txn1', 'edu.byu.isys413.data.Txn');
INSERT INTO txn VALUES ('txn1', '2013-1-12', 126.00, 23.50, 149.50, 'emp1', 'cust1', 'store1', 'payment1', 'comm1');
INSERT INTO businessobject(id, botype) VALUES ('txn2', 'edu.byu.isys413.data.Txn');
INSERT INTO txn VALUES ('txn2', '2013-1-12', 12.00, 2.34, 14.34, 'emp1', 'cust2', 'store1', 'payment2', 'comm2');
INSERT INTO businessobject(id, botype) VALUES ('txn3', 'edu.byu.isys413.data.Txn');
INSERT INTO txn VALUES ('txn3', '2013-3-12', 1171.50, 70.29, 1241.79, 'emp1', 'cust1', 'store1', 'payment3', 'comm1');
INSERT INTO businessobject(id, botype) VALUES ('txn4', 'edu.byu.isys413.data.Txn');
INSERT INTO txn VALUES ('txn4', '2013-3-12', 12.50, .75, 13.25, 'emp1', 'cust1', 'store1', 'payment4', 'comm2');




--JOURNAL ENTRY TABLE;
CREATE TABLE journalentry (
	id				VARCHAR(40) PRIMARY KEY REFERENCES businessobject(id),
	jedate			DATE,
	txnid			VARCHAR(40) REFERENCES txn(id)
);
INSERT INTO businessobject(id, botype) VALUES ('je1', 'edu.byu.isys413.data.JournalEntry');
INSERT INTO journalentry VALUES ('je1', '2012-12-31', 'txn3');
INSERT INTO businessobject(id, botype) VALUES ('je2', 'edu.byu.isys413.data.JournalEntry');
INSERT INTO journalentry VALUES ('je2', '2013-1-31', 'txn2');



--REVENUE SOURCE TABLE;
CREATE TABLE revenuesource (
	id				VARCHAR(40) PRIMARY KEY REFERENCES businessobject(id),
	chargeamount	FLOAT,
	typekind		VARCHAR(40),
	txnid			VARCHAR(40) REFERENCES txn(id)
);
--Children will insert records;


--SALE TABLE;
CREATE TABLE sale (
	id			VARCHAR(40) PRIMARY KEY REFERENCES revenuesource(id),
	quantity	INT,
	productid	VARCHAR(40) REFERENCES product(id)
);
INSERT INTO businessobject(id, botype) VALUES ('sale1', 'edu.byu.isys413.data.RevenueSource');
INSERT INTO revenuesource VALUES ('sale1', 115.23, 'sale', 'txn1');
INSERT INTO sale VALUES ('sale1', 5, 'concepprod1');
INSERT INTO businessobject(id, botype) VALUES ('sale2', 'edu.byu.isys413.data.RevenueSource');
INSERT INTO revenuesource VALUES ('sale2', 41.16, 'sale', 'txn2');
INSERT INTO sale VALUES ('sale2', 2, 'forsale1');


--RENTAL TABLE;
CREATE TABLE rental (
	id				VARCHAR(40) PRIMARY KEY REFERENCES revenuesource(id),
	dateout			DATE,
	datein			DATE,
	datedue			DATE,
	remindersent	BOOLEAN,
	forrentid		VARCHAR(40) REFERENCES forrent(id),
	employeeid		VARCHAR(40) REFERENCES employee(id),
	currentcustid	VARCHAR(40)
);
INSERT INTO businessobject VALUES ('rental1', 'edu.byu.isys413.data.Rental');
INSERT INTO revenuesource VALUES ('rental1', 286.20, 'rental', 'txn3');
INSERT INTO rental VALUES ('rental1', '2013-03-01', null, '2013-03-07', FALSE, 'forrent1', 'emp2', 'cust1');
INSERT INTO businessobject VALUES ('rental2', 'edu.byu.isys413.data.Rental');
INSERT INTO revenuesource VALUES ('rental2', 286.20, 'rental', 'txn4');
INSERT INTO rental VALUES ('rental2', '2013-3-10', '2013-3-12', '2013-3-11', FALSE, 'forrent2', 'emp2', 'cust2');


--FEE TABLE;
CREATE TABLE fee (
	id				VARCHAR(40) PRIMARY KEY REFERENCES revenuesource(id),
	amount			FLOAT,
	rentalid		VARCHAR(40) REFERENCES rental(id)
);
INSERT INTO businessobject VALUES ('fee1', 'edu.byu.isys413.data.Fee');
INSERT INTO revenuesource VALUES ('fee1', 286.20, 'rental', 'txn4');
INSERT INTO fee VALUES ('fee1', 5.00, 'rental2');



--GENERALLEDGER TABLE. Typekind is asset, expense, liability, or revenue;
CREATE TABLE generalledger (
	id				VARCHAR(40) PRIMARY KEY REFERENCES businessobject(id),
	accountname		VARCHAR(40),
	balance			FLOAT,
	typekind		VARCHAR(40)
);
INSERT INTO businessobject(id, botype) VALUES ('gl1', 'edu.byu.isys413.data.GeneralLedger');
INSERT INTO generalledger VALUES ('gl1', 'accounts payable', 505.64, 'asset');
INSERT INTO businessobject(id, botype) VALUES ('gl2', 'edu.byu.isys413.data.GeneralLedger');
INSERT INTO generalledger VALUES ('gl2', 'accounts receivable', 505.64, 'liability');

--DEBITCREDIT TABLE;
CREATE TABLE debitcredit (
	id					VARCHAR(40) PRIMARY KEY REFERENCES businessobject(id),
	debit				BOOLEAN,
	generalledgername	VARCHAR(40),
	amount				FLOAT,
	journalentryid		VARCHAR(40) REFERENCES journalentry(id),
	generalledgerid		VARCHAR(40) REFERENCES generalledger(id)
	
);
INSERT INTO businessobject(id, botype) VALUES ('dc1', 'edu.byu.isys413.data.DebitCredit');
INSERT INTO debitcredit VALUES ('dc1', true, 'cash', 500, 'je1', 'gl1');
INSERT INTO businessobject(id, botype) VALUES ('dc2', 'edu.byu.isys413.data.DebitCredit');
INSERT INTO debitcredit VALUES ('dc2', false, 'cash', 300, 'je2', 'gl1');




ALTER TABLE store ADD CONSTRAINT fk_emp FOREIGN KEY (managerid) REFERENCES employee(id);
ALTER TABLE employee ADD CONSTRAINT fk_store FOREIGN KEY (storeid) REFERENCES store(id);


