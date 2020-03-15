package nl.han.oose.dea.services;

import nl.han.oose.dea.services.dto.PlaylistDTO;
import nl.han.oose.dea.services.exceptions.IncorrectTokenException;
import nl.han.oose.dea.services.interfaces.PlaylistService;
import nl.han.oose.dea.services.interfaces.UserService;

import java.util.*;

public class SpotitubePlaylistService implements PlaylistService {
    private List<PlaylistDTO> playlistDTOS = new ArrayList<>();

    @Override
    public Map<String, Object> getAll(String token) {
        if (!token.equalsIgnoreCase(UserService.TOKEN)) {
            throw new IncorrectTokenException();
        }

        playlistDTOS = Arrays.asList(
                new PlaylistDTO(1, "Pop", true, new String[]{}),
                new PlaylistDTO(2, "Death metal", true, new String[]{}),
                new PlaylistDTO(3, "Soul", false, new String[]{})
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
