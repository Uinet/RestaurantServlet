package com.github.uinet.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<T> extends AutoCloseable {
    T create(T entity);
    Optional<T> findById(Long id);
    List<T> findAll();
    void update(T entity);
    void delete(T entity);
}
