--liquibase formatted sql

--changeset mr.rifleman:1

create table users
(
    id         serial primary key,
    email      text not null,
    first_name text,
    last_name  text,
    reg_date   timestamp not null,
    phone      text,
    role       varchar not null,
    image      text

);

create table ad
(
    id                serial  primary key,
    price             integer not null,
    title             text,
    image             text    not null,
    user_id           integer    references users(id)

);


create table comments
(
    id                serial primary key,
    text              text      not null,
    created_at        bigint    not null,
    user_id           integer      references users(id),
    ad_id             integer    references ad(id)
);



