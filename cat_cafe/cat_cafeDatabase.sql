DROP TABLE IF EXISTS bookings;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS cats;

CREATE TABLE users
(id BIGSERIAL PRIMARY KEY,
username VARCHAR(20) NOT NULL,
password VARCHAR(100) NOT NULL,
role VARCHAR(10) NOT NULL,
firstname VARCHAR(20) NOT NULL,
lastname VARCHAR(30) NOT NULL,
email VARCHAR(40) NOT NULL,
phone VARCHAR(20) NOT NULL);

INSERT INTO users (username, password, role, firstname, lastname, email, phone)
VALUES ('user', '$2a$10$7Ye2569EWEYpdUAkqPYQhOfLRjq8z.CY7yat44Ab9kfn9oGCLFwwm', 'USER', 'Maija', 'Miettunen', 'maija@gmail.com', '05034342'),
('user2', '$2a$10$.5B0Dqouq59i1C9MolDWUOGbQGJNUjhTfxzCBUdkNHY7XwUUjSCfK', 'USER', 'Erkki', 'Esimerkki', 'erkki@gmail.com', '123456'),
('admin', '$2a$10$dY9mrHPc8B9khYMbiB2HP.b..qwqCDv6yxpGTIsSu0MPPsRQMBqx6', 'ADMIN', 'Matti', 'Koski','matti@gmail.com', '05245235');

CREATE TABLE bookings
(id BIGSERIAL PRIMARY KEY,
guests SMALLINT NOT NULL,
bookingdate TIMESTAMP NOT NULL,
userid BIGINT,
FOREIGN KEY (userid) REFERENCES users(id));

INSERT INTO bookings (guests, bookingdate, userid)
VALUES (4, '2024-04-27 17:30:00', 1),
(2, '2024-04-30 12:00:00', 1),
(3, '2024-05-08 14:45:00', 2);

CREATE TABLE cats
(id BIGSERIAL PRIMARY KEY,
catname VARCHAR(100) NOT NULL,
birthdate DATE NOT NULL,
description VARCHAR(500),
imagepath VARCHAR(500));

INSERT INTO cats(catname, birthdate, description, imagepath)
VALUES ('Button', '2021-08-18', 'Loves to cuddle!', '/images/cat-649164_1280.jpg'),
('Biscuit', '2024-01-20', 'Our newest member. We will take his pictures when he is old enough to start working.', '/images/paw-print-220232_1280.jpg'),
('Lola', '2013-10-17', 'Old lady, who likes to sleep a lot.', '/images/cat-2605502_1280.jpg');
