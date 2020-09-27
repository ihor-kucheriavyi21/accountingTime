package model.entity;

import db.SQLDatabaseManager;

import java.sql.Time;
import java.util.Map;

public class User {
    public static void main(String[] args) {
        SQLDatabaseManager dbManager;
        dbManager = SQLDatabaseManager.getInstance();
        dbManager.insertTask(new Task("someTask", new Time(System.currentTimeMillis()), 9));
    }

    public static Map<Integer, Task> tasks;

    private String name;
    private int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
