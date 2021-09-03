package com.github.uinet.dao.imp;

import com.github.uinet.dao.UserDAO;
import com.github.uinet.exception.DAOException;
import com.github.uinet.model.User;
import com.github.uinet.model.UserRole;
import com.github.uinet.utils.ConnectionCreator;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class UserDAOImp implements UserDAO {

    private static final String SQL_SELECT_USER_BY_USERNAME = "SELECT * FROM users WHERE username=?";
    private static final String SQL_SELECT_ALL_USER = "SELECT * FROM users";
    private static final String SQL_CREATE_USER = "INSERT INTO users (name, password, username, money, role) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_TOP_UP_USER_BALANCE = "UPDATE users SET money=money+? WHERE id=?";

    private final Logger logger = LogManager.getLogger(UserDAO.class);

    private User extractFromResultSet(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .password(resultSet.getString("password"))
                .money(resultSet.getBigDecimal("money"))
                .username(resultSet.getString("username"))
                .role(UserRole.valueOf(resultSet.getString("role")))
                .build();
    }

    public User getUserByUsername(String username){
        User user = null;
        try (Connection connection = ConnectionCreator.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_USERNAME)){
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                user = extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Get user by username sql exception", e);
            throw new DAOException("Get user by username sql exception", e);
        }
        return user;
    }

    @Override
    public User create(User entity) throws DAOException {
        try(Connection connection = ConnectionCreator.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_USER, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getUsername());
            preparedStatement.setBigDecimal(4, entity.getMoney());
            preparedStatement.setString(5, entity.getRole().toString());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()){
                entity.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            logger.error("Create new user sql exception", e);
            throw new DAOException("Create new user sql exception", e);
        }

        return entity;
    }

    @Override
    public User findById(long userId) {
        return new User();
    }

    @Override
    public List<User> findAll() throws DAOException {
        List<User> resultList = new ArrayList<>();
        try (Connection connection = ConnectionCreator.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_USER)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ){
                User result = extractFromResultSet(resultSet);
                resultList.add(result);
            }
        } catch (SQLException e) {
            logger.error("Find all users sql exception", e);
            throw new DAOException("Find all users sql exception", e);
        }
        return resultList;
    }

    @Override
    public List<User> findAll(int page, int recordsPerPage) {
        return null;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    public void topUpBalance(long userId, BigDecimal sum) throws DAOException {
        try(Connection connection = ConnectionCreator.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_TOP_UP_USER_BALANCE)){
            preparedStatement.setBigDecimal(1, sum);
            preparedStatement.setLong(2, userId);
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            logger.error("User top up balance exception", e);
            throw new DAOException("User top up balance exception", e);
        }
    }
}
