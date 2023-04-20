-- users data

insert into user_entity (username, password, is_admin)
    values ('admin', '123', true);

insert into user_role (user_id, roles)
    values (1, 'ROLE_USER'), (1, 'ROLE_ADMIN');

insert into user_entity (username, password, is_admin)
    values ('user', '1111', false);

insert into user_role (user_id, roles)
    values (2, 'ROLE_USER');

-- projects data

insert into project_entity (name, description, deleted, user_id)
    values ('Hello World!', 'First app', false, 1);

insert into project_entity (name, description, deleted, user_id)
    values ('Admin Project', 'Check for admin role', false, 1);

insert into project_entity (name, description, deleted, user_id)
    values ('Hello World!', 'First app', false, 2);

insert into task_entity (task, deleted, project_id)
    values ('Create first class named "HelloWorld"', false, 1);

insert into task_entity (task, deleted, project_id)
    values ('Create main function', false, 1);

-- tasks data

insert into task_entity (task, deleted, project_id)
    values ('write line: System.out.println("Hello World!")', false, 1);

insert into task_entity (task, deleted, project_id)
    values ('Be happy, if you see it', false, 2);

insert into task_entity (task, deleted, project_id)
    values ('Create first class named "HelloWorld"', false, 3);

insert into task_entity (task, deleted, project_id)
    values ('Create main function', false, 3);

insert into task_entity (task, deleted, project_id)
    values ('write line: System.out.println("Hello World!")', false, 3);