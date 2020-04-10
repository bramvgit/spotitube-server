package nl.han.oose.dea.resource;

import nl.han.oose.dea.service.UserService;
import nl.han.oose.dea.service.dto.TokenDTO;
import nl.han.oose.dea.service.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class UserResourceTest {
    private UserResource sut;
    private UserService mockedUserService;

    @BeforeEach
    void setUp() {
        mockedUserService = Mockito.mock(UserService.class);

        sut = new UserResource();
        sut.setUserService(mockedUserService);
    }

    @Test
    void postLoginReturnsCorrectResponseStatus() {
        // Arrange
        UserDTO userDTO = new UserDTO("user", "pass");

        // Act
        Response response = sut.postLogin(userDTO);

        // Assert
        assertEquals(201, response.getStatus());
    }

    @Test
    void postLoginReturnsTokenDTO() {
        // Arrange
        TokenDTO tokenDTO = new TokenDTO();
        UserDTO userDTO = new UserDTO("user", "pass");
        when(mockedUserService.login(userDTO)).thenReturn(tokenDTO);

        // Act
        Response response = sut.postLogin(userDTO);

        // Assert
        assertEquals(tokenDTO, response.getEntity());
    }
}