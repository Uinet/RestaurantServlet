package com.github.uinet.dao.imp;

import com.github.uinet.dao.DishDAO;
import com.github.uinet.model.Dish;
import com.github.uinet.model.DishCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DishDAOImp implements DishDAO {

    private static final String SQL_SELECT_ALL_DISH = "SELECT * FROM dishes";

    private final Connection connection;

    public DishDAOImp(Connection connection){
        this.connection = connection;
    }

    private Dish extractFromResultSet(ResultSet resultSet) throws SQLException {
        return Dish.builder()
                .id(resultSet.getLong("id"))
                .category(DishCategory.valueOf(resultSet.getString("category")))
                .name(resultSet.getString("name"))
                .description(resultSet.getString("description"))
                .price(resultSet.getDouble("price"))
                .build();
    }

    @Override
    public void create(Dish entity) {

    }

    @Override
    public Optional<Dish> findById(Long dishId) {
        return Optional.empty();
    }

    @Override
    public List<Dish> findAll() {
        List<Dish> resultList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_DISH)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ){
                Dish result = extractFromResultSet(resultSet);
                resultList.add(result);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public void update(Dish entity) {

    }

    @Override
    public void delete(Dish entity) {

    }

    @Override
    public void close() throws Exception {

    }
}
