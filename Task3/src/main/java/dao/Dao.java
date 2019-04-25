package dao;

import java.util.List;

public interface Dao <T> {
    List<T> select(String query, Object...params);
    int create(String query, Object...params);
    int update(String query, Object...params);
    int delete(String query, Object...params);
}
