package model;

import model.entity.Task;

import java.util.Comparator;

public class Sorter {
    //todo add sort by count users
    public static final Comparator<Task> SORT_TASK_BY_NAME = Comparator.comparing(task -> task.getTaskName());
    public static final Comparator<Task> SORT_TASK_BY_CATEGORY_ENG = Comparator.comparing(task -> task.getCategory().getNameEng());
    public static final Comparator<Task> SORT_TASK_BY_CATEGORY_RU = Comparator.comparing(task -> task.getCategory().getNameRu());

    public static final Comparator<Task> SORT_TASK_BY_ID = Comparator.comparing(task -> task.getIdTask());

}

