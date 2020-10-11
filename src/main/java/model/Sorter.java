package model;

import model.entity.Task;

import java.util.Comparator;

public class Sorter {
    //todo add sort by count users
    public static final Comparator<Task> SORT_TASK_BY_NAME = Comparator.comparing(task -> task.getTaskName());
    public static final Comparator<Task> SORT_TASK_BY_CATEGORY = Comparator.comparing(task -> task.getCategory().getName());
    public static final Comparator<Task> SORT_TASK_BY_ID = Comparator.comparing(task -> task.getIdTask());

}

