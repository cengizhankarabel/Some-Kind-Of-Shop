use shopdb;


CREATE TABLE payments(
	id int PRIMARY KEY auto_increment,
    offerId int REFERENCES offers(id),
    itemId int REFERENCES items(id),
    userId int REFERENCES users(id),
    paymentAmount double NOT NULL,
    paymentStatus ENUM('WAITING', 'COMPLETED')
);

CREATE TABLE users(
	id int PRIMARY KEY auto_increment,
    firstName varchar(100) NOT NULL,
    lastName varchar(100),
    email varchar(100) NOT NULL unique,
    loginPassword varchar(100) NOT NULL,
    userType int NOT NULL
);

CREATE TABLE items(
    id int PRIMARY KEY auto_increment,
    itemName varchar(50) NOT NULL,
    itemPrice double NOT NULL,
    isAvailable boolean NOT NULL,
    userId int REFERENCES users(id)
);

 CREATE TABLE offers(
    id int PRIMARY KEY auto_increment,
    offerPrice double NOT NULL,
    itemId int REFERENCES items(id),
    userId int REFERENCES users(id),
    offerStatus ENUM('PENDING', 'REJECTED', 'ACCEPTED')
);

CREATE TABLE usertypes(
    id int PRIMARY KEY auto_increment,
    typeName varchar(100) NOT NULL
);



//password 12345
INSERT INTO users(id,firstName,lastName,email,loginPassword,userType) values (1,'customer','karabel','customer@gmail.com','$2a$12$1TlZZ4As4duscsV7wyklqOyCTOUNFf9UcS.W7cmR/UGWQPHa.Gs8a',1);
INSERT INTO users(id,firstName,lastName,email,loginPassword,userType) values (2,'employee','karabel','employee@gmail.com','$2a$12$1TlZZ4As4duscsV7wyklqOyCTOUNFf9UcS.W7cmR/UGWQPHa.Gs8a',2);
INSERT INTO users(id,firstName,lastName,email,loginPassword,userType) values (3,'manager','karabel','manager@gmail.com','$2a$12$1TlZZ4As4duscsV7wyklqOyCTOUNFf9UcS.W7cmR/UGWQPHa.Gs8a',3);


INSERT INTO usertypes(id, typeName) value (1,'customer');
INSERT INTO usertypes(id, typeName) value (2,'employee');
INSERT INTO usertypes(id, typeName) value (3,'manager');




select*from usertypes;



select*from offerstatus;

select * from items
where statusId=1 and userId=1;




select * from users;

insert into users (firstName, lastName, email) values ('Cengizhan','Karabel','cengizhankarabel@gmail.com');
insert into users (firstName, lastName, email) values ('Selin','Karabel','selinkarabel@gmail.com');
insert into users (firstName, lastName, email) values ('Chivas','Karabel','chivaskarabel@gmail.com');


insert into items (name,price,available,quantity) values ('phone',800.00,true,1);
insert into items (name,price,available,quantity) values ('notebook',1200.00,true,1);
insert into items (name,price,available,quantity) values ('keyboard',220.00,true,1);

insert into offers (price, item_id, user_id) values (820.00,1,1);
insert into offers (price, item_id, user_id) values (840.00,1,2);
insert into offers (price, item_id, user_id) values (1190.00,2,2);
insert into offers (price, item_id, user_id) values (200.00,3,3);


select * from items;
desc items;
desc users;

-- update items set id=3 where id=2;


SELECT i.id as item_id, i.name, i.price, o.price as offer_Price, o.id as offer_id, u.id as user_id, u.firstName, u.lastName
FROM items i
LEFT JOIN offers o
on i.id = o.item_id
LEFT JOIN users u
on u.id = o.user_id where u.firstName='Selin'










