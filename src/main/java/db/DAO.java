package db;

import java.util.Map;

public interface DAO<T> {
    Map<Integer, T> getAll();

    int save(T t);

    void update(T t);

    void delete(T t);

}
