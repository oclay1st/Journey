-- liquibase formatted sql

-- changeset oclay:1
-- comment: create org table
create table if not exists org (
    id serial not null constraint org_pk primary key,
    name varchar not null,
    active boolean,
    db_server varchar not null,
    db_name varchar not null,
    db_username varchar not null,
    db_password varchar,
    db_port integer not null
);
-- rollback drop table org

-- changeset oclay:2
-- comment: create unique index for id column
create unique index if not exists org_id_uindex on org (id);
-- rollback drop index if exists org_id_uindex

-- changeset oclay:3
-- comment : create unique index for name column
create unique index if not exists org_name_uindex on org (name);
-- rollback drop index if exists org_name_uindex
