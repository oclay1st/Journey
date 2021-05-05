-- liquibase formatted sql

-- changeset oclay:1
-- comment: create foriegn key on auth_user
alter table auth_user add column role_id integer;
alter table auth_user add constraint role_fk foreign key (role_id) references auth_role(id);
-- rollback alter table auth_user drop constraint role_fk
-- rollback alter table auth_user drop column role_id