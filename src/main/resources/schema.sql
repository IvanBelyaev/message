create table if not exists users (
    id bigserial,
    name varchar(50) not null unique,
    password varchar(50) not null,
    primary key (id)
);

create table if not exists messages (
    id bigserial,
    text varchar(100) not null,
    created timestamp default CURRENT_TIMESTAMP,
    user_id bigint not null,
    primary key (id),
    foreign key (user_id) references users(id)
);
