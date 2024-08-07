--liquibase formatted sql

--changeset kocman:4
INSERT INTO users (username, password, enabled)
VALUES ('ecommerceservice_user', '$2a$10$Rdeq1o9M7BD026oOBIBTBOc8UQyTfSFmJInhj9qimv6HNoHXGJs4y', true);
INSERT INTO users (username, password, enabled)
VALUES ('ecommerceservice_admin', '$2a$10$JR.9xRGx9qyW8dY.7ETf2.7BbVKoRm.sezyZsEUwKWF./W9Divgaa', true);
--rollback TRUNCATE users;

--changeset kocman:5
INSERT INTO authorities (username, authority)
VALUES ('ecommerceservice_user', 'ROLE_USER');
INSERT INTO authorities (username, authority)
VALUES ('ecommerceservice_admin', 'ROLE_ADMIN');
--rollback TRUNCATE authorities;