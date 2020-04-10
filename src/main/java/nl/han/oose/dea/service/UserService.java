package nl.han.oose.dea.service;

import nl.han.oose.dea.service.dto.TokenDTO;
import nl.han.oose.dea.service.dto.UserDTO;

public interface UserService {
    TokenDTO login(UserDTO userDTO);

    void verifyToken(String token);
}
