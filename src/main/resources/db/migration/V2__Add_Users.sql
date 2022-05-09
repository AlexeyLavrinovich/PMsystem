insert into user_entity (username, password, is_admin)
    values ('admin', '123', true);

insert into user_role (user_id, roles)
    values (1, 'ROLE_USER'), (1, 'ROLE_ADMIN');

insert into user_entity (username, password, is_admin)
    values ('user', '1111', false);

insert into user_role (user_id, roles)
    values (2, 'ROLE_USER');