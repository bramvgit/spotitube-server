package nl.han.oose.dea.services.interfaces;

import nl.han.oose.dea.services.UserService;
import nl.han.oose.dea.services.dto.TokenDTO;
import nl.han.oose.dea.services.dto.UserDTO;
import nl.han.oose.dea.services.exceptions.IncorrectLoginCredentialsException;

public class SpotitubeUserService implements UserService {
    public static final String USERNAME = "bram";
    public static final String PASSWORD = "pass";

    @Override
    public TokenDTO login(UserDTO userDTO) {
        if (correctCredentials(userDTO)) {
            return new TokenDTO("1234-1234-1234", userDTO.getUser());
        }
        throw new IncorrectLoginCredentialsException();
    }

    private boolean correctCredentials(UserDTO userDTO) {
        return userDTO.getUser().equalsIgnoreCase(USERNAME) && userDTO.getPassword().equalsIgnoreCase(PASSWORD);
    }
}
