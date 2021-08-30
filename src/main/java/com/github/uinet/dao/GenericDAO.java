package com.github.uinet.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T>{
    T create(T entity) throws SQLException;
    T findById(long id);
    List<T> findAll();
    List<T> findAll(int page, int recordsPerPage);
    void update(T entity);
    void delete(T entity);
}
