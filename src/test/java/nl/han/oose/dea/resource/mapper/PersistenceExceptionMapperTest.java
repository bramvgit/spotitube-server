package nl.han.oose.dea.resource.mapper;

import nl.han.oose.dea.exception.PersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersistenceExceptionMapperTest {
    private PersistenceExceptionMapper sut;

    @BeforeEach
    void setUp() {
        sut = new PersistenceExceptionMapper();
    }

    @Test
    void toResponseReturnsCorrectStatusCode() {
        assertEquals(400, sut.toResponse(new PersistenceException(new Exception("cause"))).getStatus());
    }
}