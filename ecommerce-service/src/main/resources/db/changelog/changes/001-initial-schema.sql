--liquibase formatted sql

--changeset kocman:1
CREATE TABLE IF NOT EXISTS price
(
    product_id INTEGER   NOT NULL,
    valid_from TIMESTAMP NOT NULL,
    valid_to   TIMESTAMP NOT NULL,
    currency   VARCHAR(255),
    value      FLOAT,
    PRIMARY KEY (product_id, valid_from, valid_to)
);
--rollback DROP TABLE price;

--changeset kocman:2
CREATE TABLE IF NOT EXISTS purchase
(
    id           SERIAL NOT NULL,
    customer_id  INTEGER,
    payment_type VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS purchase_item
(
    id             SERIAL NOT NULL,
    amount         INTEGER,
    currency       VARCHAR(255),
    price_per_unit FLOAT,
    product_id     INTEGER,
    purchase_id    INTEGER,
    PRIMARY KEY (id)
);
--rollback DROP TABLE purchase, purchase_item;

--changeset kocman:3
create table if not exists users
(
    username varchar(50) not null primary key,
    password varchar(100) not null,
    enabled  boolean not null
    );

create table if not exists authorities
(
    username  varchar(50) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key (username) references users (username)
    );
create unique index if not exists ix_auth_username on authorities (username, authority);
--rollback DROP TABLE users, authorities;