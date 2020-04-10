use Spotitube;

drop procedure if exists sp_GET_ALL_PLAYLISTS;

delimiter //
create procedure sp_GET_ALL_PLAYLISTS(iToken varchar(60))
begin
    -- Select all all playlists and add a column that expresses if the user owns the track
    select id,
           name,
           0                                                       owner,
           (select sum(duration)
            from user_token
                     inner join user_owns_playlist uop on user_token.user = uop.user
                     inner join track_in_playlist tip on tip.playlist_id = uop.playlist
                     inner join track t on tip.track_id = t.id
            group by token
            having token = iToken) length
    from playlist
    where id not in (
        select id
        from user
                 inner join user_owns_playlist uop on user.user = uop.user
                 inner join playlist p on uop.playlist = p.id
                 inner join user_token ut on user.user = ut.user
        where token = iToken
    )
    union
    select id,
           p.name,
           1                                                       owner,
           (select sum(duration)
            from user_token
                     inner join user_owns_playlist uop on user_token.user = uop.user
                     inner join track_in_playlist tip on tip.playlist_id = uop.playlist
                     inner join track t on tip.track_id = t.id
            group by token
            having token = iToken) length
    from user
             inner join user_owns_playlist uop on user.user = uop.user
             inner join playlist p on uop.playlist = p.id
             inner join user_token u on user.user = u.user
    where token = iToken;
end //
delimiter ;

-- call sp_GET_ALL_PLAYLISTS('aeb14b96-f81f-4086-b5c2-c18950e9a329');