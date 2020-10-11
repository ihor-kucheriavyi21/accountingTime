package db;

import java.util.List;
import java.util.Map;

public interface DAO<T> {
    List<T> getAll();

    int save(T t);

    void update(T t);

    void delete(T t);

}
