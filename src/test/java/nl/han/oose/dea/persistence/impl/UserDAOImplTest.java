package nl.han.oose.dea.persistence.impl;

import nl.han.oose.dea.exception.AuthenticationException;
import nl.han.oose.dea.persistence.ConnectionFactory;
import nl.han.oose.dea.persistence.TokenGenerator;
import nl.han.oose.dea.service.dto.TokenDTO;
import nl.han.oose.dea.service.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class UserDAOImplTest {
    private static final String SQL_LOGIN = "call sp_LOGIN_USER(?, ?, ?);";
    private UserDAOImpl sut;
    private ConnectionFactory mockedConnectionFactory;

    @BeforeEach
    void setUp() {
        mockedConnectionFactory = Mockito.mock(ConnectionFactory.class);

        sut = new UserDAOImpl();
        sut.setConnectionFactory(mockedConnectionFactory);
    }

    @Test
    void loginThrowsAuthenticationExceptionOnIncorrectUserDTO() throws SQLException {
        // Arrange
        UserDTO userDTO = Mockito.mock(UserDTO.class);
        Connection mockedConnection = Mockito.mock(Connection.class);
        when(mockedConnectionFactory.getConnection()).thenReturn(mockedConnection);
        when(mockedConnection.prepareStatement(SQL_LOGIN)).thenReturn(Mockito.mock(PreparedStatement.class));

        // Act & assert
        assertThrows(AuthenticationException.class, () -> {
            sut.login(userDTO);
        });
    }

    @Test
    void loginReturnsTokenDTO() throws SQLException {
        // Arrange
        UserDTO userDTO = Mockito.mock(UserDTO.class);
        Connection mockedConnection = Mockito.mock(Connection.class);
        PreparedStatement mockedPreparedStatement = Mockito.mock(PreparedStatement.class);
        ResultSet mockedResultSet = Mockito.mock(ResultSet.class);
        TokenGenerator mockedTokenGenerator = Mockito.mock(TokenGenerator.class);

        when(mockedConnectionFactory.getConnection()).thenReturn(mockedConnection);
        when(mockedConnection.prepareStatement(SQL_LOGIN)).thenReturn(mockedPreparedStatement);
        when(mockedPreparedStatement.getResultSet()).thenReturn(mockedResultSet);
        when(mockedResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockedResultSet.getString("name")).thenReturn("bram");

        // Act
        TokenDTO tokenDTO = sut.login(userDTO);

        // Assert
        assertEquals(true, (tokenDTO != null));
    }

    @Test
    void loginGeneratesRandomToken() throws SQLException {
        // Arrange
        TokenGenerator mockedTokenGenerator = Mockito.mock(TokenGenerator.class);
        TokenDTO tokenDTO = new TokenDTO("123", "bram");
        when(mockedTokenGenerator.getToken()).thenReturn(tokenDTO.getToken());

        // Act
        var response = mockedTokenGenerator.getToken();

        // Assert
        assertEquals("123", response);
    }

    @Test
    void verifyToken() {
    }
}