package nl.han.oose.dea.persistence.impl;

import nl.han.oose.dea.exception.AuthenticationException;
import nl.han.oose.dea.exception.PersistenceException;
import nl.han.oose.dea.persistence.ConnectionFactory;
import nl.han.oose.dea.persistence.TokenGenerator;
import nl.han.oose.dea.persistence.dao.UserDAO;
import nl.han.oose.dea.service.dto.TokenDTO;
import nl.han.oose.dea.service.dto.UserDTO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Default
public class UserDAOImpl implements UserDAO {
    private ConnectionFactory connectionFactory;

    @Override
    public TokenDTO login(UserDTO userDTO) {
        try (
                Connection connection = connectionFactory.getConnection();
        ) {
            TokenGenerator tokenGenerator = new TokenGenerator();
            String token = tokenGenerator.getToken();
            ResultSet resultSet = executeSQLLoginUser(userDTO, connection, token);

            if (resultSet == null) {
                throw new AuthenticationException();
            }

            return getTokenDTO(token, resultSet);
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public boolean verifyToken(String token) {
        try (
                Connection connection = connectionFactory.getConnection();
        ) {
            PreparedStatement statement = connection.prepareStatement("call sp_VERIFY_TOKEN(?);");
            statement.setString(1, token);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();

            return (resultSet != null);
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    private TokenDTO getTokenDTO(String token, ResultSet resultSet) throws SQLException {
        TokenDTO tokenDTO = null;

        while (resultSet.next()) {
            tokenDTO = new TokenDTO(token, resultSet.getString("name"));
        }
        return tokenDTO;
    }

    private ResultSet executeSQLLoginUser(UserDTO userDTO, Connection connection, String token) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("call sp_LOGIN_USER(?, ?, ?);");

        statement.setString(1, token);
        statement.setString(2, userDTO.getUser());
        statement.setString(3, userDTO.getPassword());

        statement.execute();
        return statement.getResultSet();
    }

    @Inject
    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }
}
