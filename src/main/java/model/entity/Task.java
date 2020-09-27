package model.entity;

import java.sql.Time;
import java.util.List;

public class Task {

    int idTask;
    String taskName;
    Time recordingTime;
    int amountOfTime;


    public Task(String taskName, Time startTime, int amountOfTime) {
        this.taskName = taskName;
        this.recordingTime = startTime;
        this.amountOfTime = amountOfTime;
    }

    public Task(int idTask, String taskName, Time startTime, int amountOfTime) {
        this.idTask = idTask;
        this.taskName = taskName;
        this.recordingTime = startTime;
        this.amountOfTime = amountOfTime;
    }

    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Time getRecordingTime() {
        return recordingTime;
    }

    public void setRecordingTime(Time recordingTime) {
        this.recordingTime = recordingTime;
    }

    public int getAmountOfTime() {
        return amountOfTime;
    }

    public void setAmountOfTime(int amountOfTime) {
        this.amountOfTime = amountOfTime;
    }
}
