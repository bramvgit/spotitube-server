package nl.han.oose.dea.service.impl;

import nl.han.oose.dea.persistence.impl.TrackDAOImpl;
import nl.han.oose.dea.service.dto.TrackDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class TrackServiceImplTest {
    private TrackServiceImpl sut;
    private TrackDAOImpl mockedTrackDAOImpl;

    @BeforeEach
    void setUp() {
        mockedTrackDAOImpl = Mockito.mock(TrackDAOImpl.class);

        sut = new TrackServiceImpl();
        sut.setTrackDAO(mockedTrackDAOImpl);
    }

    @Test
    void getTracksNotInPlaylistCallsCorrectMethod() {
        // Arrange
        int id = 1;

        // Act
        sut.getTracksNotInPlaylist(id);

        // Assert
        Mockito.verify(mockedTrackDAOImpl).getTracksNotInPlaylist(id);
    }

    @Test
    void getTracksInPlaylistCallsCorrectMethod() {
        // Arrange
        int id = 1;

        // Act
        sut.getTracksInPlaylist(id);

        // Assert
        Mockito.verify(mockedTrackDAOImpl).getTracksInPlaylist(id);
    }

    @Test
    void deleteTrackCallsCorrectMethod() {
        // Arrange
        int playlist = 1;
        int track = 1;

        // Act
        sut.deleteTrack(playlist, track);

        // Assert
        Mockito.verify(mockedTrackDAOImpl).deleteTrack(playlist, track);
    }

    @Test
    void addTrackCallsCorrectMethod() {
        // Arrange
        String token = "token";
        int id = 1;
        TrackDTO trackDTO = new TrackDTO("title", "performer", "album", "date", "desc", 1, 1, 1, true);

        // Act
        sut.addTrack(token, id, trackDTO);

        // Assert
        Mockito.verify(mockedTrackDAOImpl).addTrack(token, id, trackDTO);
    }
}