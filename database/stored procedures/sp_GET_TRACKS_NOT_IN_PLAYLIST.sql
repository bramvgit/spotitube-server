use Spotitube;

drop procedure if exists sp_GET_TRACKS_NOT_IN_PLAYLIST;

delimiter //
create procedure sp_GET_TRACKS_NOT_IN_PLAYLIST(iId int)
begin
    select id track_id,
           title,
           performer,
           duration,
           album,
           playcount,
           publicationDate,
           description,
           offlineAvailable
    from track
    where id not in (
        select track_id
        from track_in_playlist
        where playlist_id = iId
    );
end //
delimiter ;

-- call sp_GET_TRACKS_NOT_IN_PLAYLIST(1);