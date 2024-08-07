--liquibase formatted sql

--changeset kocman:1
CREATE TABLE IF NOT EXISTS product
(
    id          SERIAL      NOT NULL,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(500),
    weight      NUMERIC,
    width       NUMERIC,
    height      NUMERIC,
    depth       NUMERIC,
    PRIMARY KEY (id)
    );
--rollback DROP TABLE product;

--changeset kocman:2
CREATE TABLE IF NOT EXISTS product_review
(
    id          SERIAL NOT NULL,
    customer_id INTEGER NOT NULL,
    product_id  INTEGER NOT NULL,
    stars       INTEGER,
    review      VARCHAR(500),
    PRIMARY KEY (id),
    FOREIGN KEY (product_id) REFERENCES product ON DELETE CASCADE
    );
--rollback DROP TABLE product_review;

-- Default spring security schema: https://docs.spring.io/spring-security/site/docs/current/reference/html5/#user-schema
--changeset kocman:3
create table if not exists users
(
    username varchar(50) not null primary key,
    password varchar(100) not null,
    enabled  boolean                not null
    );

create table if not exists authorities
(
    username  varchar(50) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key (username) references users (username)
    );
create unique index if not exists ix_auth_username on authorities (username, authority);
--rollback DROP TABLE users, authorities;