package db;

import model.entity.Task;

import java.util.List;

public interface TaskDao extends DAO<Task> {

    @Override
    List<Task> getAll();

    @Override
    int save(Task task);

    @Override
    void update(Task task);

    @Override
    void delete(Task task);

    List<Task> getAllForCurrentUser(int idUser);

    Task getTaskById(int idTask);

}
