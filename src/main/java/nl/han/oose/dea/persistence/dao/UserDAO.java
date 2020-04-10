package nl.han.oose.dea.persistence.dao;

import nl.han.oose.dea.service.dto.TokenDTO;
import nl.han.oose.dea.service.dto.UserDTO;

public interface UserDAO {
    TokenDTO login(UserDTO userDTO);

    boolean verifyToken(String token);
}
