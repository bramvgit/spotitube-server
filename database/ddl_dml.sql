drop database if exists Spotitube;
create database Spotitube;

use Spotitube;

create table User
(
    user     varchar(60) not null,
    password varchar(60) not null,
    name     varchar(60) not null,

    primary key (user)
);

create table User_Token
(
    token          varchar(60) not null,
    user           varchar(60) not null,
    expirationDate datetime    not null,

    primary key (user, token),
    foreign key (user) references User (user)
);

create table Playlist
(
    id   int         not null,
    name varchar(60) not null,

    primary key (id)
);

create table User_Owns_Playlist
(
    user     varchar(60) not null,
    playlist int         not null,

    primary key (user, playlist),
    foreign key (user) references User (user),
    foreign key (playlist) references Playlist (id)
);

create table Track
(
    id               int         not null,
    title            varchar(60) not null,
    performer        varchar(60) not null,
    duration         varchar(60) not null,
    album            varchar(60),
    playcount        int,
    publicationDate  date,
    description      varchar(60),
    offlineAvailable tinyint(1)  not null,
    primary key (id)
);

create table Track_In_Playlist
(
    track_id    int not null,
    playlist_id int not null,

    primary key (track_id, playlist_id),
    foreign key (track_id) references Track (id),
    foreign key (playlist_id) references Playlist (id)
);