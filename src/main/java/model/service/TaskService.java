package model.service;

import db.DAO;
import db.TaskDaoImpl;
import model.entity.Task;

public class TaskService {
    DAO taskDao;

    public TaskService() {
        taskDao = new TaskDaoImpl();
    }

    public int saveTask(Task task) {
        return taskDao.save(task);
    }

    public void deleteTask(Task task) {
        taskDao.delete(task);
    }
}
