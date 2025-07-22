create database userdata;

use userdata;

create table userdb(
    username varchar(50) NOT NULL,
    password varchar(50) NOT NULL,
    salt varchar(64) NOT NULL,
    PRIMARY KEY (username)
);

CREATE TABLE notes(
    title varchar(100) NOT NULL,
    content varchar(255),
    username varchar(50) NOT NULL,
    PRIMARY KEY(title),
    FOREIGN KEY(username) REFERENCES userdb(username)
);