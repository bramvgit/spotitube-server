package nl.han.oose.dea.service;

import nl.han.oose.dea.service.dto.TrackDTO;

import javax.ws.rs.ext.Provider;
import java.util.Map;

@Provider
public interface TrackService {
    Map<String, Object> getTracksInPlaylist(int id);

    Map<String, Object> getTracksNotInPlaylist(int id);

    Map<String, Object> deleteTrack(int playlist_id, int track_id);

    Map<String, Object> addTrack(String token, int id, TrackDTO trackDTO);
}
