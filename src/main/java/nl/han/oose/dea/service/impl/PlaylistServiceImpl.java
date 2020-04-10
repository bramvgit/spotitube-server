package nl.han.oose.dea.service.impl;

import nl.han.oose.dea.persistence.dao.PlaylistDAO;
import nl.han.oose.dea.service.PlaylistService;
import nl.han.oose.dea.service.dto.PlaylistDTO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.Map;

@Default
public class PlaylistServiceImpl implements PlaylistService {
    private PlaylistDAO playlistDAO;

    @Inject
    public void setPlaylistDAO(PlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }

    @Override
    public Map<String, Object> getAll(String token) {
        return playlistDAO.getAll(token);
    }

    @Override
    public Map<String, Object> delete(String token, int id) {
        return playlistDAO.delete(token, id);
    }

    @Override
    public Map<String, Object> add(String token, PlaylistDTO playlistDTO) {
        return playlistDAO.add(token, playlistDTO);
    }

    @Override
    public Map<String, Object> editName(String token, int id, PlaylistDTO playlistDTO) {
        return playlistDAO.editName(token, id, playlistDTO);
    }
}
