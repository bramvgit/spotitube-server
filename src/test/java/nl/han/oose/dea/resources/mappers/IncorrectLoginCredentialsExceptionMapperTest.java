package nl.han.oose.dea.resources.mappers;

import nl.han.oose.dea.services.exceptions.IncorrectLoginCredentialsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;

class IncorrectLoginCredentialsExceptionMapperTest {

    private static final short HTTP_STATUS_UNAUTHORIZED = 401;

    IncorrectLoginCredentialsExceptionMapper sut;

    @BeforeEach
    void setUp() {
        sut = new IncorrectLoginCredentialsExceptionMapper();
    }

    @Test
    void IncorrectLoginCredentialsReturnsUnauthorizedStatusCode() {
        // Arrange & act
        Response response = sut.toResponse(new IncorrectLoginCredentialsException());

        // Assert
        assertEquals(HTTP_STATUS_UNAUTHORIZED, response.getStatus());
    }
}