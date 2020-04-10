package nl.han.oose.dea.resource;

import nl.han.oose.dea.service.TrackService;
import nl.han.oose.dea.service.dto.TrackDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void getTracksReturnsCorrectStatusCode() {
        // Arrange & act
        Response response = sut.getTracks("token", 10);

        // Assert
        assertEquals(200, response.getStatus());
    }

    @Test
    void getTracksInPlaylistReturnsCorrectStatusCode() {
        // Arrange & act
        Response response = sut.getTracksInPlaylist("token", 10);

        // Assert
        assertEquals(200, response.getStatus());
    }

    @Test
    void addTrackReturnsCorrectStatusCode() {
        // Arrange & act
        Response response = sut.addTrack("token", 10, Mockito.mock(TrackDTO.class));

        // Assert
        assertEquals(201, response.getStatus());
    }

    @Test
    void deleteTrackReturnsCorrectStatusCode() {
        // Arrange
        String token = "token";
        int track = 1, playlist = 1;

        // Act
        Response response = sut.deleteTrack(token, playlist, track);

        // Assert
        assertEquals(200, response.getStatus());
    }
}