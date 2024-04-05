package org.bilgeadam.repository;

import java.util.List;
import java.util.Optional;

public interface ICrud<T, ID> {
    T save(T t);

    Iterable<T> saveAll(Iterable<T> entities);

    Optional<T> findById(ID id);

    Boolean deleteById(ID id);

    Boolean existById(ID id);

    List<T> getAll();

    List<T> findByColumnAndValue(String fieldName, Object value);

    List<T> findAllEntity(T entity);

    public T update(T entity);
}
