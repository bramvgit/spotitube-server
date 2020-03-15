package nl.han.oose.dea.services;

import nl.han.oose.dea.services.dto.TokenDTO;
import nl.han.oose.dea.services.dto.UserDTO;
import nl.han.oose.dea.services.exceptions.IncorrectLoginCredentialsException;
import nl.han.oose.dea.services.interfaces.UserService;

public class SpotitubeUserService implements UserService {

    @Override
    public TokenDTO login(UserDTO userDTO) {
        if (correctCredentials(userDTO)) {
            return new TokenDTO(UserService.TOKEN, userDTO.getUser());
        }
        throw new IncorrectLoginCredentialsException();
    }

    private boolean correctCredentials(UserDTO userDTO) {
        return userDTO.getUser().equalsIgnoreCase(UserService.USERNAME) && userDTO.getPassword().equalsIgnoreCase(UserService.PASSWORD);
    }
}
