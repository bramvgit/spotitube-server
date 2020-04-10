use Spotitube;

drop procedure if exists sp_DELETE_TRACK_IN_PLAYLIST;

delimiter //
create procedure sp_DELETE_TRACK_IN_PLAYLIST(iPlaylist int, iTrack int)
begin
    delete
    from track_in_playlist
    where playlist_id = iPlaylist
      and track_id = iTrack;
end //
delimiter ;

-- call sp_DELETE_TRACK_IN_PLAYLIST(4, 9);