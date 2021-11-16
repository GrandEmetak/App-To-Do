create table categories(
 id serial primary key,
 name VARCHAR(15)
);

create table events(
 id serial primary key,
 description VARCHAR(300),
 created TIMESTAMP with time zone,
 done boolean,
 rank VARCHAR(35)
);

INSERT INTO events(description, created, done, rank)
values ('walk the dog', '2021-10-26 19:50:11.853', 'false', 'normal'),
       ('grocery shopping', '2021-10-26 19:50:11.853', 'false', 'normal'),
       ('workout legs day', '2021-10-26 19:50:11.853', 'false', 'normal');

create table users(
 id serial primary key,
 name VARCHAR(15),
 email VARCHAR(35),
 password VARCHAR(15)
);

insert into users(name, email, password, events_id) values('Petr Arsentev', 'PetrArsentev@mail.ru', 'root@tool', 1);



