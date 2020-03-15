package nl.han.oose.dea.resources;

import nl.han.oose.dea.services.exceptions.IncorrectTokenException;
import nl.han.oose.dea.services.interfaces.PlaylistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.print.attribute.IntegerSyntax;
import javax.ws.rs.core.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

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
    void returnsAllPlaylists() {
        // Arrange
        Response response;
        Map<String, Object> map;

        // Act
        response = sut.getPlaylists("token");
        map = new HashMap<>();

        // Assert
        assertEquals(map, response.getEntity());
    }

    @Test
    void getPlaylistReturnsOkStatusCode() {
        // Arrange & act
        Response response = sut.getPlaylists("token");

        // Assert
        assertEquals(200, response.getStatus());
    }

    @Test
    void getPlaylistThrowsExceptionOnIncorrectToken() {
        // Arrange
        Mockito.when(mockedPlaylistService.getAll("incorrect")).thenThrow(IncorrectTokenException.class);

        // Act & assert
        assertThrows(IncorrectTokenException.class, () -> {
            sut.getPlaylists("incorrect");
        });
    }
}