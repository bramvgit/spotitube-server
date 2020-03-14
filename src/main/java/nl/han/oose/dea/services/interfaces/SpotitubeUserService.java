package nl.han.oose.dea.services.interfaces;

import nl.han.oose.dea.services.UserService;
import nl.han.oose.dea.services.dto.TokenDTO;
import nl.han.oose.dea.services.dto.UserDTO;

public class SpotitubeUserService implements UserService {
    @Override
    public TokenDTO login(UserDTO userDTO) {
        return new TokenDTO("1234-1234-1234", userDTO.getUser());
    }
}
