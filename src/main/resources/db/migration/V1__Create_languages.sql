create table languages (
    langId int unsigned primary key auto_increment,
    welcomeMsg varchar(100) not null,
    langCode varchar(100) not null,
    carName varchar(100) not null,
    lpgOn100Km varchar(100) not null, 
    lpgPrice varchar(50) not null, 
    kmOnLpg varchar(100) not null, 
    pbOn100Km varchar(100) not null, 
    pbPrice varchar(50) not null, 
    kmOnPb varchar(100) not null,
    costDsc varchar(100) not null,
    solveButton varchar(20) not null, 
    exitButton varchar(20) not null, 
    saveProfileButton varchar(20) not null, 
    loadProfileButton varchar(20) not null
);
