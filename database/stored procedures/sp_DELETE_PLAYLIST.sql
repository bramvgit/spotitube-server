use Spotitube;

drop procedure if exists sp_DELETE_PLAYLIST;

delimiter //
create procedure sp_DELETE_PLAYLIST(iToken varchar(60), iPlaylist int)
begin
    if exists(
            select 1
            from user_owns_playlist
                     inner join user_token u on user_owns_playlist.user = u.user
            where playlist = iPlaylist
              and token = iToken
        )
    then
        delete
        from user_owns_playlist
        where playlist = iPlaylist;
        delete
        from track_in_playlist
        where playlist_id = iPlaylist;
        delete
        from playlist
        where playlist.id = iPlaylist;
    end if;
end //
delimiter ;

-- call sp_DELETE_PLAYLIST('be77c7c2-4435-40ef-b737-5b6c84d492a3', 1);