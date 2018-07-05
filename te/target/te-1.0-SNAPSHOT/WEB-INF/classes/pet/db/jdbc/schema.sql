drop table if exists user;
drop table if exists pet;
drop table if exists friend;

create table user (
  id integer identity primary key,
  name varchar(25) not null,
  pwd  varchar(25) not null,
  latitude DOUBLE ,
  longitude DOUBLE,
);
create table pet (
  id integer identity primary key,
  name varchar(25) not null,
  sex  varchar(25) ,
  species varchar(25),
  age  integer,
  masterId INTEGER not NULL ,
);

create table friend (
  id integer identity primary key,
  searchTargetId integer,
  friendId    integer,
);