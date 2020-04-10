package nl.han.oose.dea.resource.mapper;

import nl.han.oose.dea.exception.AuthenticationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthenticationExceptionMapperTest {
    private AuthenticationExceptionMapper sut;

    @BeforeEach
    void setUp() {
        sut = new AuthenticationExceptionMapper();
    }

    @Test
    void toResponseReturnsCorrectStatusCode() {
        assertEquals(401, sut.toResponse(new AuthenticationException()).getStatus());
    }
}