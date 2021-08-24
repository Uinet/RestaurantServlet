package com.github.uinet.dao.imp;

import com.github.uinet.dao.UserDAO;
import com.github.uinet.model.User;
import com.github.uinet.model.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDAOImp implements UserDAO {

    private static final String SQL_SELECT_USER_BY_USERNAME = "SELECT * FROM users INNER JOIN user_role ON users.id = user_role.user_id WHERE username=?";

    private Connection connection;

    public UserDAOImp(Connection connection){
        this.connection = connection;
    }

    private User extractFromResultSet(ResultSet resultSet) throws SQLException {
        User result = User.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .password(resultSet.getString("password"))
                .money(resultSet.getDouble("money"))
                .username(resultSet.getString("username"))
                .role(UserRole.valueOf(resultSet.getString("role")))
                .build();

        return result;
    }

    public Optional<User> getUserByUsername(String username){
        Optional<User> user = Optional.empty();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_USERNAME);
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
    public void create(User entity) {

    }

    @Override
    public Optional<User> findById(User entity) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void close() throws Exception {

    }
}
