package nl.han.oose.dea.resources;

import nl.han.oose.dea.services.exceptions.IncorrectTokenException;
import nl.han.oose.dea.services.interfaces.TrackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TrackResourceTest {
    private TrackResource sut;
    private TrackService mockedTrackService;

    @BeforeEach
    void setUp() {
        mockedTrackService = Mockito.mock(TrackService.class);

        sut = new TrackResource();
        sut.setTrackService(mockedTrackService);
    }

    @Test
    void getTracksInPlaylistReturnsOkStatusCode() {
        assertEquals(200, sut.getTracksInPlaylist("token", 1).getStatus());
    }

    @Test
    void getTracksInPlaylistThrowsExceptionOnIncorrectTokenOrIncorrectPlaylist() {
        // Arrange
        Mockito.when(mockedTrackService.getTracksInPlaylist("incorrect", 1)).thenThrow(IncorrectTokenException.class);

        // Act & assert
        assertThrows(IncorrectTokenException.class, () -> {
            mockedTrackService.getTracksInPlaylist("incorrect", 1);
        });
    }

    @Test
    void getTracksInPlaylistReturnsTracksInPlaylist() {
        // Arrange
        Response response;
        Map<String, Object> map;

        // Act
        response = sut.getTracksInPlaylist("token", 1);
        map = new HashMap<>();

        // Assert
        assertEquals(map, response.getEntity());
    }
}