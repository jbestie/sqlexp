-- =======================
-- all required sequences
-- =======================
CREATE SEQUENCE seq_task;
CREATE SEQUENCE seq_user;


-- =======================
-- tasks for solving
-- =======================
CREATE TABLE TASK
(
    ID BIGINT PRIMARY KEY,
    DESCRIPTION VARCHAR (4096) NOT NULL,
    QUERY VARCHAR (4096) NOT NULL
);
INSERT INTO TASK(ID, DESCRIPTION, QUERY) VALUES (1, 'Description','SELECT ID, NAME, ROLE, YEARS FROM EMPLOYEES ORDER BY ID');


-- =======================
-- system data
-- =======================
CREATE TABLE ROLES (
    id INTEGER PRIMARY KEY,
    name VARCHAR(64) NOT NULL
);

INSERT INTO ROLES(id, name) VALUES (1, 'USER');
INSERT INTO ROLES(id, name) VALUES (2, 'ADMIN');
INSERT INTO ROLES(id, name) VALUES (3, 'ANONYMOUS');

CREATE TABLE USERS 
(
    id BIGINT PRIMARY KEY,
    login VARCHAR(128),
    password VARCHAR(128),
    email VARCHAR(128),
    registration_date TIMESTAMP,
    role INTEGER REFERENCES ROLES(id),
    active BOOLEAN DEFAULT FALSE
);
INSERT INTO USERS(id, login, password, email, registration_date, role, active) VALUES (nextval('seq_user'), 'bestie', '$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.', 'test@email.com', now(), 1, true);

-- =======================
-- All data for tasks
-- =======================
CREATE TABLE EMPLOYEES
(
   ID BIGINT PRIMARY KEY,
   NAME VARCHAR(256) NOT NULL,
   ROLE VARCHAR(128) NOT NULL,
   YEARS FLOAT NOT NULL
);

INSERT INTO EMPLOYEES(ID, NAME, ROLE, YEARS) VALUES (1, 'John Smith', 'Manager', 1.5);
INSERT INTO EMPLOYEES(ID, NAME, ROLE, YEARS) VALUES (2, 'John Doe', 'Engineer', 5.0);
INSERT INTO EMPLOYEES(ID, NAME, ROLE, YEARS) VALUES (3, 'Amanda Brown', 'Artist', 10.5);
INSERT INTO EMPLOYEES(ID, NAME, ROLE, YEARS) VALUES (4, 'Samantha Fox', 'Singer', 3.8);
INSERT INTO EMPLOYEES(ID, NAME, ROLE, YEARS) VALUES (5, 'Michael Johns', 'Manager', 7.0);
INSERT INTO EMPLOYEES(ID, NAME, ROLE, YEARS) VALUES (6, 'Oliver Stone', 'Engineer', 2.5);
INSERT INTO EMPLOYEES(ID, NAME, ROLE, YEARS) VALUES (7, 'Enrice Eaglesias', 'Singer', 8.5);