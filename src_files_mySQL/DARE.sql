DROP SCHEMA IF EXISTS `dare`;
CREATE SCHEMA `dare`;
USE `dare`;

-- use `wight6`;

DROP TABLE IF EXISTS challenge, finished_challenge, rejected_challenge, proof, user, user_challenge;

-- create user that can complete challenges.

CREATE TABLE user (
	id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(45) UNIQUE NOT NULL,
    password TEXT NOT NULL,
	reg_date DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE challenge (
	id INT AUTO_INCREMENT PRIMARY KEY,
    pts INT NOT NULL,
    name VARCHAR(45) NOT NULL,
    description TEXT NOT NULL,
    initial_date DATETIME DEFAULT CURRENT_TIMESTAMP, -- have expiration date?
    is_pending TINYINT DEFAULT 1
);
-- 10 at a time at most

CREATE TABLE finished_challenge (
	id INT AUTO_INCREMENT PRIMARY KEY,
	challenge_id INT REFERENCES challenge(id),
    proof_id INT REFERENCES proof(id),
    complete_date DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE rejected_challenge (
	id INT AUTO_INCREMENT PRIMARY KEY,
	challenge_id INT REFERENCES challenge(id),
    reason TEXT NOT NULL,
    complete_date DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE proof (
	id INT AUTO_INCREMENT PRIMARY KEY,
    description TEXT
);

CREATE TABLE user_challenge (
	id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT REFERENCES user(id),
    finished_challenge_id INT REFERENCES finished_challenge(id)
);


