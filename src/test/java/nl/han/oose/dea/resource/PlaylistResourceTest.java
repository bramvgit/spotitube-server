package nl.han.oose.dea.resource;

import nl.han.oose.dea.service.PlaylistService;
import nl.han.oose.dea.service.dto.PlaylistDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlaylistResourceTest {
    private PlaylistResource sut;
    private PlaylistService mockedPlaylistService;

    @BeforeEach
    void setUp() {
        mockedPlaylistService = Mockito.mock(PlaylistService.class);

        sut = new PlaylistResource();
        sut.setPlaylistService(mockedPlaylistService);
    }

    @Test
    void getAllReturnsCorrectStatusCode() {
        // Arrange
        String token = "token";

        // Act
        Response response = sut.getAll(token);

        // Assert
        assertEquals(200, response.getStatus());
    }

    @Test
    void editNameReturnsCorrectStatusCode() {
        // Arrange
        String token = "token";
        int id = 1;
        PlaylistDTO playlistDTO = Mockito.mock(PlaylistDTO.class);

        // Act
        Response response = sut.editName(token, 10, playlistDTO);

        // Assert
        assertEquals(200, response.getStatus());
    }

    @Test
    void addReturnsCorrectStatusCode() {
        // Arrange
        String token = "token";
        PlaylistDTO playlistDTO = Mockito.mock(PlaylistDTO.class);

        // Act
        Response response = sut.add(token, playlistDTO);

        // Assert
        assertEquals(201, response.getStatus());
    }

    @Test
    void deleteReturnsCorrectStatusCode() {
        // Arrange
        String token = "token";
        int id = 1;

        // Act
        Response response = sut.delete(token, id);

        // Assert
        assertEquals(200, response.getStatus());
    }
}