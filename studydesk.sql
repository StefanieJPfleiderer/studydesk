DROP DATABASE IF EXISTS studydesk;

CREATE DATABASE studydesk;
USE studydesk;

CREATE TABLE category (
    ID INT(6) AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    image LONGBLOB,
    file_name VARCHAR(300));

CREATE TABLE topic (
    ID INT(6) AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    image LONGBLOB,
    file_name VARCHAR(300),
    category_id INT(6),
    FOREIGN KEY (category_id) REFERENCES category(ID) ON DELETE CASCADE);

CREATE TABLE pdf (
    ID INT(6) AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100),
    description VARCHAR(10000),
    pdf_path VARCHAR(500),
    topic_id INT(6),
    FOREIGN KEY (topic_id) REFERENCES topic(ID) ON DELETE CASCADE);

# Username and password removed for security reasons
GRANT ALL PRIVILEGES ON studydesk.* TO ''@'localhost'
IDENTIFIED BY '' WITH GRANT OPTION;