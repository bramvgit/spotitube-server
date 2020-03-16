package nl.han.oose.dea.services;

import nl.han.oose.dea.services.dto.PlaylistDTO;
import nl.han.oose.dea.services.exceptions.IncorrectTokenException;
import nl.han.oose.dea.services.interfaces.PlaylistService;
import nl.han.oose.dea.services.interfaces.UserService;

import java.util.*;

public class SpotitubePlaylistService implements PlaylistService {

    @Override
    public Map<String, Object> getAll(String token) {
        if (!token.equalsIgnoreCase(UserService.TOKEN)) {
            throw new IncorrectTokenException();
        }

        List<PlaylistDTO> playlistDTOS = Arrays.asList(
                new PlaylistDTO(1, "Pop", true, Arrays.asList()),
                new PlaylistDTO(2, "Death metal", true, Arrays.asList()),
                new PlaylistDTO(3, "Soul", false, Arrays.asList())
        );

        Map<String, Object> playlists = new HashMap<String, Object>();
        playlists.put("playlists", playlistDTOS);
        playlists.put("length", 123445);

        return playlists;
    }

    @Override
    public void delete(String token, int id) {

    }

    @Override
    public void add(String token, PlaylistDTO playlistDTO) {

    }

    @Override
    public void edit(String token, int id, PlaylistDTO playlistDTO) {

    }
}
