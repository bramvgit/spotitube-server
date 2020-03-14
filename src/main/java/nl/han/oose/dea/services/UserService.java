package nl.han.oose.dea.services;

import nl.han.oose.dea.services.dto.TokenDTO;
import nl.han.oose.dea.services.dto.UserDTO;

public interface UserService {
    TokenDTO login(UserDTO userDTO);
}
