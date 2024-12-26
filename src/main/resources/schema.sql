drop table if exists book;
create table book (
    id     int primary key not null AUTO_INCREMENT, 
    isbn   varchar(255) not null,
    title  varchar(255) not null,
    author varchar(255) not null,
    price  float not null ,
    created_date timestamp not null,
    last_modified_date timestamp not null,
    version int not null
);