drop table employee;
drop table service;
drop table member1;
drop table purchaseOrder;
drop table customer;
drop table updateStatus;

drop table productOrder;
drop table product;


create table customer
( name        CHAR(20),
  phoneNumber CHAR(20),
  gender      CHAR(1),
  PRIMARY KEY (name, phoneNumber)
);

CREATE TABLE member1
(
  accountNo       INTEGER,
  yearToDateSpent INTEGER,
  emailAddress    CHAR(40),
  password        CHAR(30),
  birthday        CHAR(20),
  currentPoints   INTEGER,
  name            CHAR(20) NOT NULL,
  phoneNumber     CHAR(20) NOT NULL,
  PRIMARY KEY (accountNo),
  FOREIGN KEY (name, phoneNumber) REFERENCES customer
);

CREATE TABLE Updatestatus
(
  accountNo       INTEGER,
  currentPoints INTEGER,
  currentStatus   CHAR(20),
  PRIMARY KEY (accountNo)
);

create table employee
(
  employeeID INTEGER NOT NULL,
  name       CHAR(20),
  PRIMARY KEY (employeeID)
);

create table service
(
  serviceID   INTEGER  NOT NULL,
  serviceDate CHAR(40),
  serviceCapacity INTEGER,
  serviceName CHAR(50), 
  PRIMARY KEY (serviceID)
);

create table product
(
  productID       INTEGER,
  price           REAL,
  brand           CHAR(40),
  inventoryNumber INTEGER,
  productType     CHAR(40),
  PRIMARY KEY (productID)
);

create table purchaseOrder
(
  purchaseID     INTEGER,
  phoneNumber     CHAR(20),
  name            CHAR(20),
  methodOfPayment CHAR(20),
  purchaseDate    CHAR(20),
  PRIMARY KEY (purchaseID),
  FOREIGN KEY (name,phoneNumber) REFERENCES customer
);

create table productOrder
(
  purchaseID INTEGER,
  productID   INTEGER,
  quantityPurchased INTEGER,
  PRIMARY KEY (purchaseID,productID),
  FOREIGN KEY (productID) REFERENCES product
);

insert into customer
    values('Elaine Wong','7789849871','F');

insert into customer
    values('Charles Roberts','7781369280','M');

insert into customer
    values('Samantha Kam','6042958321','F');

insert into customer
    values('Sophie Sanders','6135950177','F');

insert into customer
    values('Jessica Peters','6042958190','F');

insert into customer
    values('Sally Chang','7785933842','F');

insert into customer
    values('Lily Jin','6042019382','F');

insert into customer
    values('Sarah Kwong','7782341039','F');



insert into member1
    values(42590000,140,'sarah_kwong@yahoo.com','eciwhe1','1988-09-24', 280, 'Sarah Kwong','7782341039');

insert into updateStatus
    values(42590000,140, 'BeautyInsider');

insert into member1
    values(39512350,400,'jpeter@gmail.com','12kdj8*','1980-02-02', 2500 , 'Jessica Peters','6042958190');

insert into updateStatus
    values(39512350,400, 'VIB');

insert into member1
    values(91827400,1600,'lililili@yahoo.com','qowieh*4','1990-10-24', 3000, 'Lily Jin','6042019382');

insert into updateStatus
    values(91827400,1600, 'VIB Rouge');

insert into member1
    values(12304509,234,'sally_change@outlook.com','57dlfn*3lk','1997-01-14', 380, 'Sally Chang','7785933842');

insert into updateStatus
    values(12304509,234, 'BeautyInsider');

insert into member1
    values(18572039,600,'sophies@gmail.com','dlvnei$7','1992-06-18', 700, 'Sophie Sanders','6135950177');

insert into updateStatus
    values(18572039 ,600, 'VIB');

insert into employee
    values(13579,'John Smith');

insert into employee
    values(12345,'Elizabeth Martin');

insert into employee
    values(12645,'Adam Smith');

insert into employee
    values(19283,'Jane Doe');

insert into employee
    values(87364,'Max Lee');

insert into employee
    values(36475,'Carter Wong');

insert into service
    values(123456,'2016-2-15',20,'make-up tutorial');

insert into service
    values(111222,'2016-3-21',20, 'brow-waxing');

insert into service
    values(333444,'2016-4-2',20, 'celebration');

insert into service
    values(555666,'2016-1-18',20,'make-up tutorial set 2');

insert into service
    values(124356,'2016-7-28',20, 'VIB gift');

insert into service
    values(765432,'2016-8-24',20, 'meet the artist');

insert into product
    values(5555,64.99,'Becca',53, 'makeup');

insert into product
    values(6969,78.99,'Guerlain',20, 'makeup');

insert into product
    values(9999,0.00,'Givenchy',5, 'pointPerks');

insert into product
    values(8123,24.99,'GlamGlow',30, 'skinCare');

insert into product
    values(1000,10.99,'OPI',199, 'nail');

insert into product
    values(5151,129.99,'Caudalie',29, 'skinCare');

insert into product
    values(3333,41.99,'Chloe',24, 'fragrance');


insert into productOrder
    values(68019349,5555,1);

insert into purchaseOrder
    values(68019349,'7789849871', 'Elaine Wong', 'credit','2016-07-07');

insert into productOrder
    values(22304709,3333,2);

insert into purchaseOrder
    values(22304709,'7781369280', 'Charles Roberts', 'cash','2016-07-23');

insert into productOrder
    values(32709384,5151,1);

insert into purchaseOrder
    values(32709384,'6042958321', 'Samantha Kam', 'debit','2016-08-12');

insert into productOrder
    values(49572746,5151,3);

insert into purchaseOrder
    values(49572746,'7782341039', 'Sarah Kwong','credit','2016-09-30');

insert into productOrder
    values(56983098,8123,5);

insert into purchaseOrder
    values(56983098,'6042958190', 'Jessica Peters', 'credit','2016-01-01');

insert into productOrder
    values(79823094,1000,1);

insert into purchaseOrder
    values(79823094,'7785933842', 'Sally Chang', 'debit','2016-07-23');

insert into productOrder
    values(55726347,6969,1);

insert into purchaseOrder
    values(55726347,'6042019382', 'Lily Jin', 'cash','2016-08-09');

insert into productOrder
    values(74923748,6969,1);

insert into purchaseOrder
    values(74923748,'6135950177', 'Sophie Sanders', 'credit','2016-07-23');


