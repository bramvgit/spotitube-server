package nl.han.oose.dea.resources;

import nl.han.oose.dea.services.UserService;
import nl.han.oose.dea.services.dto.UserDTO;
import nl.han.oose.dea.services.exceptions.IncorrectLoginCredentialsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;

class UserResourceTest {

    private static final short HTTP_STATUS_CREATED = 201;

    private UserResource sut;
    private UserService mockedUserService;

    @BeforeEach
    void setUp() {
        mockedUserService = Mockito.mock(UserService.class);

        sut = new UserResource();
        sut.setUserService(mockedUserService);
    }

    @Test
    void correctLoginCredentialsReturnsCreatedStatusCode() {
        // Arrange
        UserDTO userDTO = new UserDTO("user", "pass");

        // Act
        Response response = sut.postLogin(userDTO);

        // Assert
        Mockito.verify(mockedUserService).login(userDTO);
        assertEquals(HTTP_STATUS_CREATED, response.getStatus());
    }

    @Test
    void IncorrectLoginCredentialsThrowsUnauthorized() {
        // Arrange
        UserDTO userDTO = new UserDTO("user", "pass");
        Mockito.when(mockedUserService.login(userDTO)).thenThrow(IncorrectLoginCredentialsException.class);

        // Act & assert
        assertThrows(IncorrectLoginCredentialsException.class, () -> {
            sut.postLogin(userDTO);
        });
    }
}