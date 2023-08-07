--liquibase formatted sql

--changeset mr.rifleman:1

create table users
(
    id         serial primary key,
    email      text not null,
    first_name text,
    last_name  text,
    phone      text,
    role       varchar not null,
    image      text

);

create table comments
(
    id                serial primary key,
    created_at        timestamp not null,
    author_image      text      not null,
    author_first_name text      not null,
    txt               text      not null
    FOREIGN KEY (user_id) REFERENCES users(ID)
);

create table ad
(
    id                serial primary key,
    user_id           integer not null,
    price             integer not null,
    title             text,
    image             text    not null
    FOREIGN KEY (user_id) REFERENCES users (ID)
    FOREIGN KEY (ad_id) REFERENCES ad(ID)

);


