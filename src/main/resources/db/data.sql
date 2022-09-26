-- This file must be named 'data.sql' and will be used to populate test database when using @Entity (JPA) for tests

-- This is seed data for users table
TRUNCATE TABLE "users" RESTART IDENTITY CASCADE;

-- These are the unencrypted passwords:

-- INSERT INTO "users" ("username", "password", "enabled") VALUES
-- ('Benjamin', 'helloworld78', 'true'),
-- ('Chris', '12345678@', 'true'),
-- ('Sophia', 'Fabulous44', 'true'),
-- ('Xiaoyu', 'tekkenrules99', 'true'),
-- ('Walter White', 'ihatemywife123', 'true'),
-- ('Timothy', 'timtamareyum123', 'true'),
-- ('Dave The Rave', 'illegalusernamelul55', 'true'),
-- ('Yasmin', 'QuEeNyAsMiN01', 'true'),
-- ('Scarlet', 'istomponurcorpse777', 'true'),
-- ('Aoife', 'itspronouncedeefa!!!', 'true'),
-- ('Eoin', 'itspronouncedowen!!!', 'true'),
-- ('Mario', 'itsamemario!', 'true');

-- These are the encrypted passwords:

INSERT INTO "users" ("username", "password", "enabled") VALUES
('Benjamin', '$2a$10$Q2SOVeeMsS12GcQfYGma6OwlAT/MeYO3QHIgOCCBWOBDU0q0EKHY6', 'true'),
('Chris', '$2a$10$psnEupfHrjS.oV5dQAy2yuVopl2K.6ComU2o5HDVgz47IMbyXJjwK', 'true'),
('Sophia', '$2a$10$os4xIh6HqYLdkmJ8mKmeyuX5coA4eGkdjNFOzx2.mZHfA41aSqsWK', 'true'),
('Xiaoyu', '$2a$10$O7sCK3BnFqaigMrDSIGFGOIX.tRLJTfHlVSURMxqRqQ2llM.eaKCO', 'true'),
('Walter White', '$2a$10$h0qZrNQmv4eIXYG7GMOh/.LcVsOhoWcJZo0t2wgGy4RsxmDIWQlOu', 'true'),
('Timothy', '$2a$10$OFFLATJiNopow1VlA8fRH.GRw5QkCRir0eLP8a1HVqu5U7e84e8LC', 'true'),
('Dave The Rave', '$2a$10$iYWNmNNDdoKNogNl2UVyfOr9pSx1X2pX.goE8gqZ6tpbiCpKiRWPm', 'true'),
('Yasmin', '$2a$10$/WKTLmvdu8etToeRq.fMf.440sir/F8uFbXoqxJ4ie27.bOcmut9G', 'true'),
('Scarlet', '$2a$10$9N8LYElYaMYdEzSjXMd7ROnPd9HjbNbay7s4kreztuGsj9pShLRD6', 'true'),
('Aoife', '$2a$10$4XYHuQmKp4R5/ZCXRakQauGPQ40B2S0zP6udL0oKc3DzFB09ldj46', 'true'),
('Eoin', '$2a$10$rxnvRWqMnkDlV/smgj6aHe77ET3HB9rC.4kZGUf7Uqm.Oputl2Am6', 'true'),
('Mario', '$2a$10$248UWI9RG4m.pXbWhTlZ4eGW51E1vUuLRfmIZV2thqOjefFbXkcIa', 'true');

-- This is seed data for friends table
TRUNCATE TABLE "friends" RESTART IDENTITY CASCADE;

INSERT INTO "friends" ("requester_id", "requestee_id", "request_status", "low_id", "high_id") VALUES
('1', '4', 'accepted', '1', '4'),
('1', '3', 'pending', '1', '3'),
('5', '6', 'blocked', '5', '6'),
('7', '8', 'accepted', '7', '8'),
('4', '12', 'accepted', '4', '12'),
('12', '9', 'pending', '9', '12'),
('5', '4', 'blocked', '4', '5'),
('8', '10', 'pending', '8', '10'),
('11', '1', 'accepted', '1', '11'),
('7', '12', 'accepted', '7', '12'),
('11', '10', 'pending', '10', '11'),
('1', '8', 'pending', '1', '8');

-- This is seed data for authorities table
TRUNCATE TABLE "authorities" RESTART IDENTITY CASCADE;

INSERT INTO "authorities" ("username", "authority") VALUES
('Benjamin', 'ROLE_USER'),
('Chris', 'ROLE_USER'),
('Sophia', 'ROLE_USER'),
('Xiaoyu', 'ROLE_USER'),
('Walter White', 'ROLE_USER'),
('Timothy', 'ROLE_USER'),
('Dave The Rave', 'ROLE_USER'),
('Yasmin', 'ROLE_USER'),
('Scarlet', 'ROLE_USER'),
('Aoife', 'ROLE_USER'),
('Eoin', 'ROLE_USER'),
('Mario', 'ROLE_USER');