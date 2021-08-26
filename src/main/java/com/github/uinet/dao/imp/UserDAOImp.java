package com.github.uinet.dao.imp;

import com.github.uinet.dao.UserDAO;
import com.github.uinet.model.User;
import com.github.uinet.model.UserRole;

import javax.jws.soap.SOAPBinding;
import java.sql.*;
import java.util.*;

public class UserDAOImp implements UserDAO {

    private static final String SQL_SELECT_USER_BY_USERNAME = "SELECT * FROM users WHERE username=?";
    private static final String SQL_SELECT_ALL_USER = "SELECT * FROM users";
    private static final String SQL_CREATE_USER = "INSERT INTO users (name, password, username, money, role) VALUES (?, ?, ?, ?, ?)";

    private Connection connection;

    public UserDAOImp(Connection connection){
        this.connection = connection;
    }

    private User extractFromResultSet(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .password(resultSet.getString("password"))
                .money(resultSet.getDouble("money"))
                .username(resultSet.getString("username"))
                .role(UserRole.valueOf(resultSet.getString("role")))
                .build();
    }

    public Optional<User> getUserByUsername(String username){
        Optional<User> user = Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_USERNAME)){
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                user = Optional.of(extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User create(User entity) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_USER, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getUsername());
            preparedStatement.setDouble(4, entity.getMoney());
            preparedStatement.setString(5, entity.getRole().toString());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()){
                entity.setId(rs.getLong(1));
            }
        }

        return entity;
    }

    @Override
    public User findById(Long userId) {
        return new User();
    }

    @Override
    public List<User> findAll() {
        List<User> resultList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_USER)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ){
                User result = extractFromResultSet(resultSet);
                resultList.add(result);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
