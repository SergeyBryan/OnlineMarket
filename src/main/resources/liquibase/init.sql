--liquibase formatted sql

--changeset mr.rifleman:1

create table users
(
    id         serial primary key,
    email      text not null,
    first_name text,
    last_name  text,
    phone      text,
    role       text not null,
    image      bigint
);

create table comments
(
    id                serial primary key,
    created_at        timestamp not null,
    author_image      text      not null,
    author_first_name text      not null,
    txt               text      not null
);

create table extended_ad
(
    id                serial primary key,
    author_first_name text    not null,
    author_last_name  text    not null,
    email             text    not null,
    phone             text    not null,
    price             integer not null,
    description       text    not null,
    title             text,
    image             text    not null
);


