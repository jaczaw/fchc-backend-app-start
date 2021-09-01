
create table users (
    id integer NOT NULL,
    username varchar ( 50 ) not null,
    password varchar ( 100 ) not null,
    enabled boolean not null,
    UNIQUE(username),
    CONSTRAINT users_PK PRIMARY KEY (ID)
);

create table authorities (
    username varchar ( 50 ) not null,
    authority varchar ( 50 ) not null,
    constraint fk_authorities_users foreign key (username) references users(username)
);

insert into users (id, username, password, enabled) values (1, 'test', '{bcrypt}$2a$10$upzXFsFUOClFRR69OMKF8eajGMRs0vhcSHqvNDKy9yfW45w7o9z6O', true);
insert into authorities (username, authority) values ('test','USER');