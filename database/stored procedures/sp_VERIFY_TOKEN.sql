use Spotitube;

drop procedure if exists sp_VERIFY_TOKEN;

delimiter //
create procedure sp_VERIFY_TOKEN(iToken varchar(60))
begin
    if not exists(
            select token
            from user_token
            where token = iToken
              and expirationDate > current_timestamp()
        )
    then
        delete
        from user_token
        where expirationDate <= current_timestamp;
    else
        select 1;
    end if;
end //
delimiter ;

-- call sp_VERIFY_TOKEN('incorrect token');
