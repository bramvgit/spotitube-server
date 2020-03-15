package nl.han.oose.dea.services.interfaces;

import nl.han.oose.dea.services.dto.TokenDTO;
import nl.han.oose.dea.services.dto.UserDTO;

public interface UserService {
    String USERNAME = "bram";
    String PASSWORD = "pass";
    String TOKEN = "1234-1234-1234";

    TokenDTO login(UserDTO userDTO);
}
