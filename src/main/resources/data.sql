DROP TABLE IF EXISTS USER_EXA;
 
CREATE TABLE USER_EXA (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  ap VARCHAR(250) NOT NULL,
  username VARCHAR(250) NOT NULL,
  address VARCHAR(250) DEFAULT NULL,
  email VARCHAR(250) DEFAULT NULL,
);

INSERT INTO USER_EXA
VALUES(10001,'Zaragoza', 'Ale', 'Troncoso', 'Ale@gmail');

INSERT INTO USER_EXA
VALUES(10002,'Gomez', 'Jorge', 'Azucena', 'Jorge@gmail');