package nl.han.oose.dea.services.interfaces;

import nl.han.oose.dea.services.dto.PlaylistDTO;

import java.util.Map;

public interface PlaylistService {
    Map<String, Object> getAll(String token);
    void delete(String token, int id);
    void add(String token, PlaylistDTO playlistDTO);
    void edit(String token, int id, PlaylistDTO playlistDTO);
}
