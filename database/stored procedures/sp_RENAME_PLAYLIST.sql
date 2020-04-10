use Spotitube;

drop procedure if exists sp_RENAME_PLAYLIST;

delimiter //
create procedure sp_RENAME_PLAYLIST(iToken varchar(60), iPlaylist int, iName varchar(60))
begin
    if not exists(
            select 1
            from user_owns_playlist
                     inner join user_token ut on user_owns_playlist.user = ut.user
            where token != iToken
              and playlist != iPlaylist
        )
    then
        update playlist
            inner join user_token
            inner join user_owns_playlist uop on user_token.user = uop.user
            inner join playlist p on uop.playlist = p.id
        set playlist.name = iName
        where token = iToken
          and playlist.id = iPlaylist;
    end if;
end //
delimiter ;

-- call sp_RENAME_PLAYLIST('3d50c689-a2c4-4528-ad54-6f6d1a9fd0f0', 1, 'Moonshine');