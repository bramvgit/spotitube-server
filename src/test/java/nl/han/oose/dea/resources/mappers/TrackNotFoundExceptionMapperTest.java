package nl.han.oose.dea.resources.mappers;

import nl.han.oose.dea.services.exceptions.TrackNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrackNotFoundExceptionMapperTest {
    private TrackNotFoundExceptionMapper sut;

    @BeforeEach
    void setUp() {
        sut = new TrackNotFoundExceptionMapper();
    }

    @Test
    void returnsNotFoundStatusCode() {
        assertEquals(404, sut.toResponse(new TrackNotFoundException()).getStatus());
    }
}