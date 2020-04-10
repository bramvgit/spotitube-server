package nl.han.oose.dea.persistence.impl;

import nl.han.oose.dea.exception.PersistenceException;
import nl.han.oose.dea.persistence.ConnectionFactory;
import nl.han.oose.dea.persistence.dao.TrackDAO;
import nl.han.oose.dea.service.dto.TrackDTO;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrackDAOImpl implements TrackDAO {
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_PERFORMER = "performer";
    private static final String COLUMN_ALBUM = "album";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_TRACK_ID = "track_id";
    private static final String COLUMN_DURATION = "duration";
    private static final String COLUMN_PLAYCOUNT = "playcount";
    private static final String COLUMN_OFFLINE_AVAILABLE = "offlineAvailable";
    private static final String COLUMN_PUBLICATION_DATE = "publicationDate";

    private ConnectionFactory connectionFactory;

    @Override
    public Map<String, Object> getTracksInPlaylist(int id) {
        try (Connection connection = connectionFactory.getConnection()) {
            //Map<String, Object> tracksInPlaylist = new HashMap<>();

            PreparedStatement statement = connection.prepareStatement("call sp_GET_TRACKS_IN_PLAYLIST(?);");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            Map<String, Object> tracksInPlaylist = new HashMap<>();
            List<TrackDTO> trackDTOS = getTracks(resultSet);
            tracksInPlaylist.put("tracks", trackDTOS);

            return tracksInPlaylist;
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public Map<String, Object> getTracksNotInPlaylist(int id) {
        try (Connection connection = connectionFactory.getConnection()) {
            Map<String, Object> tracksInPlaylist = new HashMap<>();

            PreparedStatement statement = connection.prepareStatement("call sp_GET_TRACKS_NOT_IN_PLAYLIST(?);");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            List<TrackDTO> trackDTOS = getTracks(resultSet);
            tracksInPlaylist.put("tracks", trackDTOS);

            return tracksInPlaylist;
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    private List<TrackDTO> getTracks(ResultSet resultSet) throws SQLException {
        List<TrackDTO> trackDTOS = new ArrayList<>();

        while (resultSet.next()) {
            trackDTOS.add(
                    new TrackDTO(resultSet.getString(COLUMN_TITLE),
                            resultSet.getString(COLUMN_PERFORMER),
                            resultSet.getString(COLUMN_ALBUM),
                            getPublicationDate(resultSet),
                            resultSet.getString(COLUMN_DESCRIPTION),
                            resultSet.getInt(COLUMN_TRACK_ID),
                            resultSet.getInt(COLUMN_DURATION),
                            resultSet.getInt(COLUMN_PLAYCOUNT),
                            (resultSet.getInt(COLUMN_OFFLINE_AVAILABLE) == 1)
                    )
            );
        }
        return trackDTOS;
    }

    @Override
    public Map<String, Object> deleteTrack(int playlist_id, int track_id) {
        try (
                Connection connection = connectionFactory.getConnection()
        ) {
            PreparedStatement statement = connection.prepareStatement("call sp_DELETE_TRACK_IN_PLAYLIST(?, ?);");
            statement.setInt(1, playlist_id);
            statement.setInt(2, track_id);
            statement.executeQuery();

            return getTracksInPlaylist(playlist_id);
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public Map<String, Object> addTrack(String token, int id, TrackDTO trackDTO) {
        try (
                Connection connection = connectionFactory.getConnection()
        ) {
            PreparedStatement statement = connection.prepareStatement("call sp_ADD_TRACK_SP(?, ?, ?, ?)");

            statement.setInt(1, id);
            statement.setInt(2, trackDTO.isOfflineAvailable() ? 1 : 0);
            statement.setInt(3, trackDTO.getId());
            statement.setString(4, token);
            statement.executeQuery();

            return getTracksInPlaylist(id);
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    private String getPublicationDate(ResultSet resultSet) throws SQLException {
        if (resultSet.getDate(COLUMN_PUBLICATION_DATE) != null) {
            return resultSet.getDate(COLUMN_PUBLICATION_DATE).toString();
        }
        return null;
    }

    @Inject
    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }
}
