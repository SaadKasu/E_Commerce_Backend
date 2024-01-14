create table IF NOT EXISTS Users
(
    created_date       datetime(6)  not null,
    id                 varchar(255) primary key,
    last_modified_date datetime(6)  not null,
    created_by         varchar(255) not null,
    last_modified_by   varchar(255) not null,
    name               varchar(255) not null,
    firstName          varchar(255) not null,
    lastName           varchar(255) not null,
    middleName         varchar(255),
    email              varchar(255) not null,
    mobileNumber       varchar(255) not null,
    age                int                  ,
    gender             varchar(255)
);