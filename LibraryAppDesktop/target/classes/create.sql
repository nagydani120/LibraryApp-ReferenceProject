CREATE DATABASE IF NOT EXISTS libapp;
USE libapp;
CREATE TABLE IF NOT EXISTS libadmin (id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,username varchar(50) NOT NULL,email varchar(60) NOT NULL,password varchar (200) NOT NULL);
CREATE TABLE IF NOT EXISTS libuser  (id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,first_name varchar(50) NOT NULL,last_name varchar(50) NOT NULL,date_of_birth DATE NOT NULL,gender varchar(10),address varchar(70),phone varchar(30),email varchar(70) NOT NULL UNIQUE,password varchar (200) NOT NULL,active BOOLEAN DEFAULT TRUE NOT NULL );
CREATE TABLE IF NOT EXISTS books (id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, title varchar(80) NOT NULL, publication_year INTEGER, author varchar(80) NOT NULL, isbn varchar(30), genre varchar(20), times_borrowed INTEGER NOT NULL DEFAULT 0, actually_borrowed BOOLEAN DEFAULT FALSE,deleted BOOLEAN DEFAULT FALSE);
CREATE TABLE IF NOT EXISTS borrows (borrow_id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, member_id INTEGER NOT NULL,book_id INTEGER NOT NULL,borrowing_day DATE NOT NULL,expected_return DATE NOT NULL,return_date DATE DEFAULT NULL);
ALTER TABLE libapp.borrows ADD FOREIGN KEY (book_id) REFERENCES books(id);
ALTER TABLE libapp.borrows ADD FOREIGN KEY (member_id) REFERENCES libuser(id);
