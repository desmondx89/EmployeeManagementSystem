delete from roles where role_id > 2;

insert into user_roles (role_id, user_id) values (1,1);
insert into user_roles (role_id, user_id) values (2,1);