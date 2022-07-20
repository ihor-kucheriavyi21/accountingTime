package model.service;

import db.TaskDao;
import model.Sorter;
import model.entity.Task;

import java.util.Collections;
import java.util.List;

public class TaskService {
    private TaskDao taskDao;

    public TaskService(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public int saveTask(Task task) {
        return taskDao.save(task);
    }

    public void deleteTask(Task task) {
        taskDao.delete(task);
    }

    public void updateTask(Task task) {
        taskDao.update(task);
    }

    public List getAllTask() {
        return taskDao.getAll();
    }


    public List<Task> getAllTaskForCurrentUser(int idUser) {
        return taskDao.getAllForCurrentUser(idUser);
    }

    public Task getTaskById(int idTask) {
        return taskDao.getTaskById(idTask);
    }


    public List getAllTaskSorted(int numSort, String locale) {
        List<Task> tasks = taskDao.getAll();
        switch (numSort) {
            case 0:
                Collections.sort(tasks, Sorter.SORT_TASK_BY_ID);
                break;
            case 1:
                Collections.sort(tasks, Sorter.SORT_TASK_BY_NAME);
                break;
            case 2:

                if (locale.equals("en_EN"))
                    Collections.sort(tasks, Sorter.SORT_TASK_BY_CATEGORY_ENG);
                else
                    Collections.sort(tasks, Sorter.SORT_TASK_BY_CATEGORY_RU);
                break;
        }
        return tasks;
    }

    public boolean requestForAddTaskIsValid(String name, String time, String category) {
        return name != null && name.length() > 2 &&
                time != null && time.length() > 0 &&
                category != null && category.length() > 0;
    }

}
