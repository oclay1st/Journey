-- liquibase formatted sql

-- changeset oclay:1
-- comment: create privilege table
create table if not exists auth_privilege(
    id serial not null constraint privilege_pk primary key,
    name varchar(255) not null
);
-- rollback drop table auth_privilege

-- changeset oclay:2
-- comment: create unique index for id on auth_privilege table
create unique index if not exists privilege_id_uindex on auth_privilege(id)
-- rollback drop index privilege_id_uindex

-- changeset oclay:3
-- comment: create unique index for name on auth_privilege table
create unique index if not exists privilege_name_uindex on auth_privilege(name)
-- rollback drop index privilege_name_uindex