package model.entity;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Time;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class TaskTest {
    private Task task;
    private Task task1;
    private Task task2;
    private Task task3;
    private Task task4;

    @BeforeMethod
    public void createTask() {
        task = new Task(1, "name", new Time(System.currentTimeMillis()), 4, 1, 1);
        task1 = new Task(1, "name2", new Time(System.currentTimeMillis()), 4, 1);
        task2 = new Task("name2", new Time(System.currentTimeMillis()), 4, 1);
        task3 = new Task(1, "name2", new Time(System.currentTimeMillis()), 4,
                new Category("EngName", "RuName"), 1, 2);
        task4 = new Task("name2", new Time(System.currentTimeMillis()), 4, new Category("engName", "ruName"), 1);

    }

    @Test
    public void testConstructorTask() {
        assertThat(task.amountOfTime).isEqualTo(4);
        task.setTaskName("");
        assertThat(task.taskName).isEmpty();
    }

    @Test
    public void testListTasks() {
        User user = new User();
        user.setTasks(Arrays.asList(task, task1, task2, task3));
        assertThat(user.getTasks()).contains(task);
        assertThat(user.getTasks()).isNotEmpty();
        assertThat(user.getTasks()).doesNotContainNull();
        assertThat(user.getTasks()).doesNotContain(task4);
    }
}
