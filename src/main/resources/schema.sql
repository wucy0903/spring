DROP TABLE IF EXISTS USERS2;
CREATE TABLE USERS2(ID SERIAL PRIMARY KEY AUTO_INCREMENT,
USERNAME VARCHAR(255), HASHPASSWORD VARCHAR(255));