package nl.han.oose.dea.services;

import nl.han.oose.dea.services.dto.PlaylistDTO;
import nl.han.oose.dea.services.dto.TrackDTO;
import nl.han.oose.dea.services.exceptions.IncorrectTokenException;
import nl.han.oose.dea.services.exceptions.TrackNotFoundException;
import nl.han.oose.dea.services.interfaces.TrackService;
import nl.han.oose.dea.services.interfaces.UserService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpotitubeTrackService implements TrackService {

    @Override
    public Map<String, Object> getTracksInPlaylist(String token, int id) {
        if (!token.equalsIgnoreCase(UserService.TOKEN)) {
            throw new IncorrectTokenException();
        }

        List<PlaylistDTO> playlistDTOS = Arrays.asList(
                new PlaylistDTO(1, "Pop", true, Arrays.asList(
                        new TrackDTO("Hear me", "Reed John", "The Reeds", "18-03-2001", "The long song", 1, 120, 4, true),
                        new TrackDTO("Sing with me", "Reed John", "The Reeds", "18-03-2001", "The long song", 2, 120, 4, false),
                        new TrackDTO("Good songs", "Reed John", "The Reeds", "18-03-2001", "The long song", 3, 120, 4, false)
                )),
                new PlaylistDTO(2, "Death Metal", true, Arrays.asList(
                        new TrackDTO("Hear me2", "Reed John", "The Reeds", "18-03-2001", "The long song", 1, 120, 4, true),
                        new TrackDTO("Sing with me2", "Reed John", "The Reeds", "18-03-2001", "The long song", 2, 120, 4, false),
                        new TrackDTO("Good songs2", "Reed John", "The Reeds", "18-03-2001", "The long song", 3, 120, 4, false)
                )),
                new PlaylistDTO(3, "Jazz", true, Arrays.asList(
                        new TrackDTO("Hear me3", "Reed John", "The Reeds", "18-03-2001", "The long song", 1, 120, 4, true),
                        new TrackDTO("Sing with me3", "Reed John", "The Reeds", "18-03-2001", "The long song", 2, 120, 4, false),
                        new TrackDTO("Good songs3", "Reed John", "The Reeds", "18-03-2001", "The long song", 3, 120, 4, false)
                ))
        );

        Map<String, Object> tracksInPlaylist = new HashMap<String, Object>();
        PlaylistDTO playlist = playlistDTOS.stream().filter(p -> p.getId() == id).findFirst().orElseThrow(TrackNotFoundException::new);
        tracksInPlaylist.put("tracks", playlist.getTracks());

        return tracksInPlaylist;
    }
}
