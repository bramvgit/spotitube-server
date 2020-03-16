package nl.han.oose.dea.services.interfaces;

import javax.ws.rs.ext.Provider;
import java.util.Map;

@Provider
public interface TrackService {
    Map<String, Object> getTracksInPlaylist(String token, int id);
}
