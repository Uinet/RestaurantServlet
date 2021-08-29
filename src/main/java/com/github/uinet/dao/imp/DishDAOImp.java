package com.github.uinet.dao.imp;

import com.github.uinet.dao.DishDAO;
import com.github.uinet.model.Dish;
import com.github.uinet.model.DishCategory;
import com.github.uinet.utils.ConnectionCreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DishDAOImp implements DishDAO {

    private static final String SQL_SELECT_ALL_DISH = "SELECT * FROM dishes LIMIT ?, ?";
    private static final String SQL_SELECT_DISH_BY_ID ="SELECT * FROM dishes WHERE id=?";
    private static final String SQL_DELETE_DISH = "DELETE FROM dishes WHERE id=?";
    private static final String SQL_SELECT_DISHES_BY_CATEGORY = "SELECT * FROM dishes WHERE category=? LIMIT ?, ?";
    private static final String SQL_UPDATE_DISH = "UPDATE dishes SET price=?, name=?, description=?, category=? WHERE id=?";
    private static final String SQL_CREATE_DISH = "INSERT INTO dishes (price, name, description, category) VALUES (?, ?, ?, ?)";
    private static final String SQL_GET_COUNT_OF_DISHES = "SELECT COUNT(id) AS row_count FROM dishes";

    private Dish extractFromResultSet(ResultSet resultSet) throws SQLException {
        return Dish.builder()
                .id(resultSet.getLong("id"))
                .category(DishCategory.valueOf(resultSet.getString("category")))
                .name(resultSet.getString("name"))
                .description(resultSet.getString("description"))
                .price(resultSet.getBigDecimal("price"))
                .image(resultSet.getString("img"))
                .build();
    }

    @Override
    public Dish create(Dish entity) {
        return null;
    }

    @Override
    public Dish findById(long dishId) {
        Dish dish = null;
        try(Connection connection = ConnectionCreator.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_DISH_BY_ID);){
            preparedStatement.setLong(1, dishId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){
                dish = extractFromResultSet(rs);
            }

        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
        return dish;
    }

    @Override
    public List<Dish> findAll(){
        return new ArrayList<>();
    }

    @Override
    public List<Dish> findAll(int page, int recordsPerPage) {
        List<Dish> resultList = new ArrayList<>();
        try (Connection connection = ConnectionCreator.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_DISH)){
            preparedStatement.setInt(1, page * recordsPerPage - recordsPerPage);
            preparedStatement.setInt(2, recordsPerPage);
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

    public List<Dish> findAllByCategory(DishCategory dishCategory, int page, int recordsPerPage ) {
        List<Dish> result = new ArrayList<>();
        try(Connection connection = ConnectionCreator.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_DISHES_BY_CATEGORY);){
            preparedStatement.setString(1, dishCategory.toString());
            preparedStatement.setInt(2, page * recordsPerPage - recordsPerPage);
            preparedStatement.setInt(3, recordsPerPage);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                result.add(extractFromResultSet(rs));
            }

        }catch (SQLException ex){
            throw new RuntimeException();
        }
        return result;
    }

    public int getNumberOfRows(){
        int result = 0;
        try(Connection connection = ConnectionCreator.createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_COUNT_OF_DISHES)){
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                result = resultSet.getInt("row_count");
            }
        }
        catch (SQLException exception){
            throw new RuntimeException();
        }
        return result;
    }
}
