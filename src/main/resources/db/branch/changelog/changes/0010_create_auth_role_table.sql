-- liquibase formatted sql

-- changeset oclay:1
-- commet: create auth_role table
create table if not exists auth_role(
    id serial not null constraint role_pk primary key,
    name varchar(20) not null
);
-- rollback drop table auth_role

-- chageset oclay:2
--comment: create unique index for id on auth_role
create unique index if not exists role_id_uindex on auth_role(id);
--rollback drop index role_id_uindex

-- changeset oclay:3
--comment: create unique index for name on auth_role
create unique index if not exists role_name_uindex on auth_role(name)
--rollback drop index role_name_uindex