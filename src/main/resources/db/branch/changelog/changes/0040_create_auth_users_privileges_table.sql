-- liquibase formatted sql

--changeset oclay:1
-- comment: create auth_users_privileges tables
create table if not exists auth_users_privileges(
    user_id int references auth_user(id) on update cascade on delete cascade,
    privilege_id int references auth_privilege(id) on update cascade on delete cascade,
    constraint users_privileges_pk primary key (user_id, privilege_id)
);
-- rollback drop table auth_users_privileges