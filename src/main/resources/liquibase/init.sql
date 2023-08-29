--liquibase formatted sql

--changeset mr.rifleman:1

create table users
(
    id         bigserial primary key,
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
    id          bigserial primary key,
    price       integer not null,
    title       text,
    description text,
    image       text    not null,
    user_id     bigint references users (id)

);


create table comment
(
    id                bigserial primary key,
    text              text      not null,
    created_at        bigint    not null,
    user_id           bigint      references users(id),
    ad_id             bigint    references ad(id)
);



