package com.github.uinet.dao;

import com.github.uinet.exception.DAOException;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T>{
    T create(T entity) throws SQLException, DAOException;
    T findById(long id) throws DAOException;
    List<T> findAll() throws DAOException;
    List<T> findAll(int page, int recordsPerPage) throws DAOException;
    void update(T entity) throws DAOException;
    void delete(T entity);
}
