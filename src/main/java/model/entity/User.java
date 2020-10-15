package model.entity;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {


    public List<Task> tasks;
    private int id;

    private String name;
    private String pass;
    private int idRole;


    public User(int id, String name, String pass, int idRole) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.idRole = idRole;
    }

    public User(String name, String pass, int idRole) {
        this.name = name;
        this.pass = pass;
        this.idRole = idRole;
    }

    public User() {
    }

    public User(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    public User(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                ", idRole=" + idRole +
                '}';
    }
}
