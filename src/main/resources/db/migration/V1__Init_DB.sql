create table user_entity (
    id BIGINT auto_increment,
    username varchar(255) not null unique,
    password varchar(255) not null,
    is_admin boolean,
    primary key (id)
);

create table user_role (
    user_id BIGINT auto_increment,
    roles varchar(255)
);

create table project_entity (
    id BIGINT auto_increment,
    name varchar(255) not null,
    description varchar(255) not null,
    user_id BIGINT,
    deleted boolean,
    primary key (id)
);

create table task_entity (
    id BIGINT auto_increment,
    task varchar(255) not null,
    project_id BIGINT,
    deleted boolean,
    primary key (id)
);

alter table if exists project_entity
        add constraint project_entity_fk
        foreign key (user_id) references user_entity;

alter table if exists user_role
        add constraint user_entity_fk
        foreign key (user_id) references user_entity;

alter table if exists task_entity
        add constraint task_entity_fk
        foreign key (project_id) references project_entity;