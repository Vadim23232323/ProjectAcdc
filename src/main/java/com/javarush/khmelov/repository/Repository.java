package com.javarush.khmelov.repository;

import java.util.Collection;
import java.util.Optional;

public interface Repository<T> {

    Collection<T> getAll();

    Optional<T> get(long id);

    void create(T entity);

    void update(T entity);

    void delete(T entity);
}
