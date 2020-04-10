package nl.han.oose.dea.service.impl;

import nl.han.oose.dea.exception.IncorrectTokenException;
import nl.han.oose.dea.persistence.dao.UserDAO;
import nl.han.oose.dea.persistence.impl.UserDAOImpl;
import nl.han.oose.dea.service.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;

class UserServiceImplTest {
    private UserServiceImpl sut;
    private UserDAO mockedUserDAO;

    @BeforeEach
    void setUp() {
        sut = new UserServiceImpl();

        mockedUserDAO = Mockito.mock(UserDAOImpl.class);
        sut.setUserDAO(mockedUserDAO);
    }

    @Test
    void loginCallsCorrectMethod() {
        // Arrange
        UserDTO userDTO = new UserDTO("bram", "pass");

        // Act
        sut.login(userDTO);

        // Assert
        Mockito.verify(mockedUserDAO).login(userDTO);
    }

    @Test
    void verifyTokenCallsCorrectMethod() {
        // Arrange
        String token = "token";
        Mockito.when(mockedUserDAO.verifyToken(token)).thenReturn(true);

        // Act
        mockedUserDAO.verifyToken(token);

        // Assert
        Mockito.verify(mockedUserDAO).verifyToken(token);
    }

    @Test
    void verifyTokenThrowsExceptionOnIncorrectToken() {
        // Arrange
        String token = "token";
        Mockito.when(mockedUserDAO.verifyToken(token)).thenReturn(false);

        // Act
        assertThrows(IncorrectTokenException.class, () -> sut.verifyToken(token));
    }
}