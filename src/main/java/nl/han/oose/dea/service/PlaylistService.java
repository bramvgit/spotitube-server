package nl.han.oose.dea.service;

import nl.han.oose.dea.service.dto.PlaylistDTO;

import java.util.Map;

public interface PlaylistService {
    Map<String, Object> getAll(String token);

    Map<String, Object> delete(String token, int id);

    Map<String, Object> add(String token, PlaylistDTO playlistDTO);

    Map<String, Object> editName(String token, int id, PlaylistDTO playlistDTO);
}
