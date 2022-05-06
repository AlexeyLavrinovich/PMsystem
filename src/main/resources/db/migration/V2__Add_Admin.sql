insert into user_entity (id, username, password, is_admin)
    values (1, 'admin', '123', true);

insert into user_role (user_id, roles)
    values (1, 'ROLE_USER'), (1, 'ROLE_ADMIN');