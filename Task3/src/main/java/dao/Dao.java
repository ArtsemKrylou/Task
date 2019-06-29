package dao;

import java.util.List;

public interface Dao<T> {
    List<T> getAll();

    void update(T entity);

    T getEntityById(Long id);

    void delete(Long id);

    T create(T entity);
}
