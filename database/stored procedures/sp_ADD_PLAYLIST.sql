use Spotitube;

drop procedure if exists sp_ADD_PLAYLIST;

delimiter //
create procedure sp_ADD_PLAYLIST(iToken varchar(60), iName varchar(60))
begin
    set @playlist_id = (select id + 1
                        from playlist
                        order by id desc
                        limit 1);

    set @user = (select user.user
                 from user
                          inner join user_token ut on user.user = ut.user
                 where token = iToken);

    insert into playlist (id, name)
    values (@playlist_id, iName);

    insert into user_owns_playlist (user, playlist)
    values (@user, @playlist_id);
end //
delimiter ;

-- call sp_ADD_PLAYLIST('548fc85e-27c9-43dc-a558-8cafa1cb2fa7', 'The blue skies');