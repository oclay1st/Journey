-- liquibase formatted sql

-- changeset oclay:1
-- comment: create auth_roles_privileges table
create table if not exists auth_roles_privileges(
    role_id int references auth_role(id) on update cascade on delete cascade,
    privilege_id int references auth_privilege(id) on update cascade on delete cascade,
    constraint roles_privileges_pk primary key (role_id, privilege_id)
)
-- rollback drop table auth_roles_privileges