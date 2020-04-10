-- This filename ends with '_SP' because for some reason Intelij wouldn't accept the name sp_ADD_TRACK.sql

use Spotitube;

drop procedure if exists sp_ADD_TRACK_SP;

delimiter //
create procedure sp_ADD_TRACK_SP(iPlaylist int,
                                 iOfflineAvailable tinyint(1),
                                 iTrack int,
                                 iToken varchar(60))
begin
    if exists(
            select 1
            from user_owns_playlist
            where playlist = iPlaylist
              and user in (
                select user
                from user_token
                where token = iToken
            )
        )
    then
        insert into track_in_playlist (track_id, playlist_id) values (iTrack, iPlaylist);
    end if;
end
//
delimiter ;

/*
call sp_ADD_TRACK_SP(
        '123',
        1,
        'The Golden Sun',
        'John Doe',
        120,
        'The God Son',
        55,
        '2020-03-23',
        'Good Song',
        1,
        1
    );
 */