create table IF NOT EXISTS UserSessions
(
    created_date       datetime(6)  not null,
    id                 varchar(255) primary key,
    last_modified_date datetime(6)  not null,
    created_by         varchar(255) not null,
    last_modified_by   varchar(255) not null,
    sessionToken       varchar(255) not null unique,
    startedAt          datetime(6) not null ,
    endedAt            datetime(6) not null ,
    user_id            varchar(255)
);