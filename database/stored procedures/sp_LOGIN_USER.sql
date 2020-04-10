use Spotitube;

drop procedure if exists sp_LOGIN_USER;

delimiter //
create procedure sp_LOGIN_USER(iToken varchar(60), iUser varchar(60), iPassword varchar(60))
begin
    if exists(
            select user
            from user
            where user = iUser
              and password = iPassword
        )
    then
        insert into user_token (token, user, expirationDate)
        values (iToken, iUser, date_add(current_timestamp(), interval 2 hour));

        select name
        from user
        where user = iUser;
    end if;
end //
delimiter ;

-- call sp_LOGIN_USER('token', 'bram', 'pass');