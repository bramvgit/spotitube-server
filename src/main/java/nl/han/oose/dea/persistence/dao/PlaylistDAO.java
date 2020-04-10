package nl.han.oose.dea.persistence.dao;

import nl.han.oose.dea.service.dto.PlaylistDTO;

import java.util.Map;

public interface PlaylistDAO {
    Map<String, Object> getAll(String token);

    Map<String, Object> delete(String token, int id);

    Map<String, Object> add(String token, PlaylistDTO playlistDTO);

    Map<String, Object> editName(String token, int id, PlaylistDTO playlistDTO);
}
