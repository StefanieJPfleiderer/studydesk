DROP DATABASE IF EXISTS studydesk;

CREATE DATABASE studydesk;
USE studydesk;

CREATE TABLE category (
    ID INT(6) AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    image LONGBLOB);

# Username and password removed for security reasons
GRANT ALL PRIVILEGES ON studydesk.* TO 'root'@'localhost'
IDENTIFIED BY 'admin' WITH GRANT OPTION;