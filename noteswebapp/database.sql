CREATE DATABASE userdata;

USE userdata;

CREATE TABLE userdb(
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    salt VARCHAR(64) NOT NULL,
    PRIMARY KEY (username)
);

CREATE TABLE notes(
    ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    content VARCHAR(255),
    username VARCHAR(50) NOT NULL,
    FOREIGN KEY(username) REFERENCES userdb(username)
);