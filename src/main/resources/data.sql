insert into ROLES (NAME) values ('ADMIN');
insert into ROLES (NAME) values ('USER');

insert into USERS (user_id, username, enabled, password) values (1, 'admin', true, '$2y$12$LbNWLhG3lTqm4skk6VvZ8.9HYSy.SuPJoY6vudpQw49UYcd2XINky');

insert into user_roles (role_id, user_id) values (1,1);
insert into user_roles (role_id, user_id) values (2,1);