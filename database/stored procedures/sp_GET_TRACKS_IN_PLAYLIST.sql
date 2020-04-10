use Spotitube;

drop procedure if exists sp_GET_TRACKS_IN_PLAYLIST;

delimiter //
create procedure sp_GET_TRACKS_IN_PLAYLIST(iId int)
begin
    select track_id,
           title,
           performer,
           duration,
           album,
           playcount,
           publicationDate,
           description,
           offlineAvailable
    from track_in_playlist
             inner join track t on track_in_playlist.track_id = t.id
    where playlist_id = iId;
end //
delimiter ;

-- call sp_GET_TRACKS_IN_PLAYLIST(1);