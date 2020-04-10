package nl.han.oose.dea.persistence.impl;

import nl.han.oose.dea.exception.PersistenceException;
import nl.han.oose.dea.persistence.ConnectionFactory;
import nl.han.oose.dea.persistence.dao.PlaylistDAO;
import nl.han.oose.dea.service.dto.PlaylistDTO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Default
public class PlaylistDAOImpl implements PlaylistDAO {
    private ConnectionFactory connectionFactory;

    @Inject
    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public Map<String, Object> getAll(String token) {
        try (
                Connection connection = connectionFactory.getConnection()

        ) {
            PreparedStatement statement = connection.prepareStatement("call sp_GET_ALL_PLAYLISTS(?);");
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();

            Map<String, Object> playlists = new HashMap<>();
            List<PlaylistDTO> playlistDTOList = new ArrayList<>();

            while (resultSet.next()) {
                playlistDTOList.add(
                        new PlaylistDTO(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                (resultSet.getInt("owner") > 0),
                                Collections.emptyList()
                        )
                );
                if (!playlistDTOList.contains("length")) {
                    playlists.put("length", resultSet.getInt("length"));
                }
            }
            playlists.put("playlists", playlistDTOList);
            return playlists;
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public Map<String, Object> delete(String token, int id) {
        try (
                Connection connection = connectionFactory.getConnection()
        ) {
            PreparedStatement statement = connection.prepareStatement("call sp_DELETE_PLAYLIST(?, ?);");
            statement.setString(1, token);
            statement.setInt(2, id);
            statement.executeQuery();

            return getAll(token);
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public Map<String, Object> add(String token, PlaylistDTO playlistDTO) {
        try (
                Connection connection = connectionFactory.getConnection()
        ) {
            PreparedStatement statement = connection.prepareStatement("call sp_ADD_PLAYLIST(?, ?)");
            statement.setString(1, token);
            statement.setString(2, playlistDTO.getName());
            statement.executeQuery();

            return getAll(token);
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public Map<String, Object> editName(String token, int id, PlaylistDTO playlistDTO) {
        try (
                Connection connection = connectionFactory.getConnection()
        ) {
            PreparedStatement statement = connection.prepareStatement("call sp_RENAME_PLAYLIST(?, ?, ?);");
            statement.setString(1, token);
            statement.setInt(2, id);
            statement.setString(3, playlistDTO.getName());
            statement.executeQuery();

            return getAll(token);
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }
}
