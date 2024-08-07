--liquibase formatted sql

--changeset kocman:4
INSERT INTO users (username, password, enabled)
VALUES ('productservice_user', '$2a$10$LRDGGMMZl5SjgDOWxucK..rS/UUWhXSf8hCy/C0YneMnZETU1hUf2', true);
INSERT INTO users (username, password, enabled)
VALUES ('productservice_admin', '$2a$10$uVsDGdOuIDBlmOCHKq2P4OEYjXn6FfKtYXy9A6oAdOJgSpn9g1GZu', true);
--rollback TRUNCATE users;

--changeset kocman:5
INSERT INTO authorities (username, authority)
VALUES ('productservice_user', 'ROLE_USER');
INSERT INTO authorities (username, authority)
VALUES ('productservice_admin', 'ROLE_ADMIN');
--rollback TRUNCATE authorities;