CREATE SCHEMA `vladdb`;

USE `vladdb`;

create table users (
       id bigint not null auto_increment,
        email varchar(40) not null ,
        first_name varchar(20) not null,
        last_name varchar(20) not null,
        password varchar(64) not null,
        primary key (id)
    )engine=InnoDB;
    
create table assets(
   id bigint not null auto_increment,
	userid bigint ,
    email varchar(40) not null ,
	name varchar(45) not null,
    price bigint,
    amount bigint,
    purchaseDate Date,
    sellOrBuy boolean, 
	primary key (id),
    FOREIGN KEY (userid) REFERENCES users(id)
);
    
    
INSERT INTO users (id,email,first_name,last_name,password) VALUES ('2', '123@gmail.com', 'Vlad', 'Borungel', '$2a$10$xiR5rLI8ozyBICmYKH9j0.N0PwSDBVK1LG6G9VwUPs9.Y8mJC48Xe');

INSERT INTO assets (userid, email, name, price, amount, purchaseDate, sellOrBuy)
VALUES
    (1, 'vladyvlady95@gmail.com', 'ethereum', 10000, 1, '2022-05-05', 0);