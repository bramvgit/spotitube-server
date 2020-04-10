package nl.han.oose.dea.service.impl;

import nl.han.oose.dea.exception.IncorrectTokenException;
import nl.han.oose.dea.persistence.dao.UserDAO;
import nl.han.oose.dea.service.UserService;
import nl.han.oose.dea.service.dto.TokenDTO;
import nl.han.oose.dea.service.dto.UserDTO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    @Override
    public TokenDTO login(UserDTO userDTO) {
        return userDAO.login(userDTO);
    }

    @Override
    public void verifyToken(String token) {
        if (!userDAO.verifyToken(token)) {
            throw new IncorrectTokenException();
        }
    }

    @Inject
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
