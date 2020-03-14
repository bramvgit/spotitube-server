package nl.han.oose.dea.services;

import nl.han.oose.dea.services.dto.TokenDTO;
import nl.han.oose.dea.services.dto.UserDTO;

public class UserService {
    public TokenDTO login(UserDTO userDTO) {
        return new TokenDTO("1234-1234-1234", userDTO.getUser());
    }
}
