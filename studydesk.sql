DROP DATABASE IF EXISTS studydesk;

CREATE DATABASE studydesk;
USE studydesk;

CREATE TABLE category (
    ID INT(6) AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    image LONGBLOB);

CREATE TABLE topic (
    ID INT(6) AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    image LONGBLOB,
    category_id INT(6),
    FOREIGN KEY (category_id) REFERENCES category(ID) ON DELETE CASCADE);

# Username and password removed for security reasons
GRANT ALL PRIVILEGES ON studydesk.* TO ''@'localhost'
IDENTIFIED BY '' WITH GRANT OPTION;