-- tasks for solving
DROP TABLE IF EXISTS TASK;

-- internal user's data
DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS ROLES;

-- all other stuff
DROP TABLE IF EXISTS EMPLOYEES;

-- drop sequences
DROP SEQUENCE IF EXISTS seq_task_category;
DROP SEQUENCE IF EXISTS seq_task;
DROP SEQUENCE IF EXISTS seq_user;