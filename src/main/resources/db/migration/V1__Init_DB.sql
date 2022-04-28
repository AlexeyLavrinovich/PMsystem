create sequence hibernate_sequence start 1 increment 1;

create table user_entity (
    id int8 not null,
    username varchar(255) not null unique,
    password varchar(255) not null,
    role int4,
    is_admin boolean,
    primary key (id)
);

create table project_entity (
    id int8 not null,
    name varchar(255) not null,
    user_id int8,
    primary key (id)
);

alter table if exists project_entity
        add constraint project_entity_fk
        foreign key (user_id) references user_entity;