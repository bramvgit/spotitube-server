package nl.han.oose.dea.persistence.dao;

import nl.han.oose.dea.service.dto.TrackDTO;

import java.util.Map;

public interface TrackDAO {
    Map<String, Object> getTracksInPlaylist(int id);

    Map<String, Object> getTracksNotInPlaylist(int id);

    Map<String, Object> deleteTrack(int playlist_id, int track_id);

    Map<String, Object> addTrack(String token, int id, TrackDTO trackDTO);
}
