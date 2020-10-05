package model.entity;

import java.sql.Time;

public class Task {

    int idTask;
    String taskName;
    Time recordingTime;
    int amountOfTime;
    int idUser;
    int idStatus;


    public Task(int idTask, String taskName, Time recordingTime, int amountOfTime, int idStatus) {
        this.idTask = idTask;
        this.taskName = taskName;
        this.recordingTime = recordingTime;
        this.amountOfTime = amountOfTime;
        this.idStatus = idStatus;
    }

    public Task(String taskName, Time recordingTime, int amountOfTime, int idUser) {
        this.taskName = taskName;
        this.recordingTime = recordingTime;
        this.amountOfTime = amountOfTime;
        this.idUser = idUser;
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

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }
}
