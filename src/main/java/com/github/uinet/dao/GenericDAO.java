package com.github.uinet.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<T> extends AutoCloseable {
    void create(T entity);
    Optional<T> findById(T entity);
    List<T> findAll();
    void update(T entity);
    void delete(T entity);
}
