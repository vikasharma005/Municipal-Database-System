CREATE DATABASE IF NOT EXISTS MunicipalDB;
USE MunicipalDB;

CREATE TABLE IF NOT EXISTS admins (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL
);

INSERT INTO admins (username, password) VALUES ('admin', 'admin');
INSERT INTO admins (username, password) VALUES ('vikas', 'vikas');
INSERT INTO admins (username, password) VALUES ('tanishq', 'tanishq');

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL
);

INSERT INTO users (username, password) VALUES ('user', 'user');
INSERT INTO users (username, password) VALUES ('vikas', 'vikas');
INSERT INTO users (username, password) VALUES ('tanishq', 'tanishq');

CREATE TABLE IF NOT EXISTS Information (
    ID INT(5) AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    DOB DATE NOT NULL,
    Occupation VARCHAR(50),
    FamilyType VARCHAR(10),
    Status VARCHAR(15)
);

INSERT INTO Information (ID, Name, DOB, Occupation, FamilyType, Status)
VALUES
    (10001, 'John Doe', '1990-01-15', 'Engineer', 'Single', 'Active'),
    (10002, 'Jane Smith', '1985-05-20', 'Doctor', 'Joint', 'Active');
