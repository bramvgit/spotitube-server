use Spotitube;

delete
from user_owns_playlist;
delete
from track_in_playlist;
delete
from track;
delete
from playlist;
delete
from user_token;
delete
from user;

insert into User (user, password, name)
values ('bram', 'pass', 'Bram Verdouw'),
       ('john', 'pass22', 'John Person');

insert into Playlist (id, name)
values (1, 'The Blue Zone'),
       (2, 'Chill Days'),
       (3, 'Random');

insert into Track (id, title, performer, duration, album, playcount, publicationDate, description, offlineAvailable)
values (1, 'The Wind', 'John Blue', 120, 'John is Cool', 33, '2020-01-10', 'Written by John', 1),
       (2, 'Blue Turtle', 'John Blue', 300, null, null, null, null, 0),
       (3, 'Sing Along', 'Nick Red', 222, 'Sing With Me', 2, null, 'Written by Nick', 1);


insert into track_in_playlist (track_id, playlist_id)
values (1, 1),
       (2, 1),
       (3, 2),
       (3, 3);

insert into user_owns_playlist (user, playlist)
values ('bram', 1),
       ('john', 3);