package nl.han.oose.dea.service.impl;

import nl.han.oose.dea.persistence.dao.PlaylistDAO;
import nl.han.oose.dea.service.dto.PlaylistDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;

class PlaylistServiceImplTest {
    private PlaylistServiceImpl sut;
    private PlaylistDAO mockedPlaylistDAO;

    @BeforeEach
    void setUp() {
        mockedPlaylistDAO = Mockito.mock(PlaylistDAO.class);

        sut = new PlaylistServiceImpl();
        sut.setPlaylistDAO(mockedPlaylistDAO);
    }

    @Test
    void getAllCallsCorrectMethod() {
        // Arrange
        String token = "token";

        // Act
        sut.getAll(token);

        // Assert
        Mockito.verify(mockedPlaylistDAO).getAll(token);
    }

    @Test
    void deleteCallsCorrectMethod() {
        // Arrange
        String token = "token";
        int id = 1;

        // Act
        sut.delete(token, id);

        // Assert
        Mockito.verify(mockedPlaylistDAO).delete(token, id);
    }

    @Test
    void addCallsCorrectMethod() {
        // Arrange
        String token = "token";
        PlaylistDTO playlistDTO = new PlaylistDTO(1, "name", true, Collections.emptyList());

        // Act
        sut.add(token, playlistDTO);

        // Assert
        Mockito.verify(mockedPlaylistDAO).add(token, playlistDTO);
    }

    @Test
    void editNameCallsCorrectMethod() {
        // Arrange
        String token = "token";
        int id = 1;
        PlaylistDTO mockedPlaylistDTO = Mockito.mock(PlaylistDTO.class);

        // Act
        sut.editName(token, id, mockedPlaylistDTO);

        // Assert
        Mockito.verify(mockedPlaylistDAO).editName(token, id, mockedPlaylistDTO);
    }
}