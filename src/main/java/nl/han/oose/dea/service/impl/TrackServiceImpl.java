package nl.han.oose.dea.service.impl;

import nl.han.oose.dea.persistence.dao.TrackDAO;
import nl.han.oose.dea.service.TrackService;
import nl.han.oose.dea.service.dto.TrackDTO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.Map;

@Default
public class TrackServiceImpl implements TrackService {
    private TrackDAO trackDAO;

    @Override
    public Map<String, Object> getTracksInPlaylist(int id) {
        return trackDAO.getTracksInPlaylist(id);
    }

    @Override
    public Map<String, Object> getTracksNotInPlaylist(int id) {
        return trackDAO.getTracksNotInPlaylist(id);
    }

    @Override
    public Map<String, Object> deleteTrack(int playlist_id, int track_id) {
        return trackDAO.deleteTrack(playlist_id, track_id);
    }

    @Override
    public Map<String, Object> addTrack(String token, int id, TrackDTO trackDTO) {
        return trackDAO.addTrack(token, id, trackDTO);
    }

    @Inject
    public void setTrackDAO(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }
}
