package db;

import java.util.List;

public interface DAO<T> {
    List<T> getAll();

    int save(T t);

    void update(T t);

    void delete(T t);

}
