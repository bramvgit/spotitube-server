package nl.han.oose.dea.resource.mapper;

import nl.han.oose.dea.exception.IncorrectTokenException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IncorrectTokenExceptionMapperTest {
    private IncorrectTokenExceptionMapper sut;

    @BeforeEach
    void setUp() {
        sut = new IncorrectTokenExceptionMapper();
    }

    @Test
    void toResponseReturnsCorrectStatusCode() {
        assertEquals(403, sut.toResponse(new IncorrectTokenException()).getStatus());
    }
}