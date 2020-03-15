package nl.han.oose.dea.resources.mappers;

import nl.han.oose.dea.services.exceptions.IncorrectTokenException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IncorrectTokenExceptionMapperTest {

    private IncorrectTokenExceptionMapper sut;

    @BeforeEach
    void setUp() {
        sut = new IncorrectTokenExceptionMapper();
    }

    @Test
    void ReturnsForbiddenStatusCode() {
        assertEquals(403, sut.toResponse(new IncorrectTokenException()).getStatus());
    }
}