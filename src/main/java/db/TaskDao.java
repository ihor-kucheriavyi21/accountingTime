package db;

import model.entity.Task;

import java.util.Map;

public interface TaskDao extends DAO<Task> {

    @Override
    Map getAll();

    @Override
    int save(Task task);

    @Override
    void update(Task task);

    @Override
    void delete(Task task);

    Map<Integer, Task> getAllForCurrentUser(int idUser);

    Task getTaskById(int idTask);

}
