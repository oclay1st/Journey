-- liquibase formatted sql

-- changeset oclay:1
-- comment: create auth_user table
create table if not exists auth_user(
    id serial not null constraint user_pk primary key,
    username varchar(30),
    password varchar(255),
    name varchar(30),
    last_name varchar(60),
    email varchar(60) not null,
    active boolean
);
-- rollback drop table auth_user

-- changeset oclay:2
-- comment: create unique index for id on auth_user table
create unique index if not exists user_id_uindex on auth_user(id);
-- rollback drop index if exists user_id_uindex

-- changeset oclay:3
-- comment: create unique index for usermame on auth_user table
create unique index if not exists user_username_uindex on auth_user(username);
-- rollback drop index if exists user_username_uindex

-- changeset oclay:4
-- comment: create unique index for email on auth_user table
create unique index if not exists user_email_uindex on auth_user(email);
-- rollback drop index if exists user_email_uindex