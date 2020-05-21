create table cars (
    id int unsigned primary key auto_increment,
    username varchar(100) not null,
    name varchar(100) not null,
    onPowered bit,
    onOn100km double(10.2) not null,
    lpgPowered bit,
    lpgOn100Km double(10.2) not null,
    pbPowered bit,
    pbOn100Km double(10.2) not null
);