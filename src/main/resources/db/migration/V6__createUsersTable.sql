create table users (
    id int unsigned primary key auto_increment,
    username varchar(100) not null,
    role varchar(100) not null,
    password varchar(100) not null
);