create table IF NOT EXISTS Sessions
(
    created_date       datetime(6)  not null,
    id                 varchar(255) primary key,
    last_modified_date datetime(6)  not null,
    ends_at            datetime(6)  not null,
    created_by         varchar(255) not null,
    last_modified_by   varchar(255) not null,
    user_id            varchar(255) not null,
    token              varchar(255) not null,
    FOREIGN KEY (user_id) REFERENCES users (id)
);