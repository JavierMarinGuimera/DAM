 CREATE TABLE users(
   id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
                  username VARCHAR(30) NOT NULL,
                  name  VARCHAR(20) NOT NULL,
                  email VARCHAR(50) NOT NULL,
                  password VARCHAR(50) NOT NULL,
                  rank INT DEFAULT 0,
                  active BOOLEAN DEFAULT true,
                  created_on TIMESTAMP DEFAULT CURRENT_TIME);                               