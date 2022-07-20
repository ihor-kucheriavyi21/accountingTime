package model.service;

import factory.ServiceFactory;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TaskServiceTest {
    TaskService taskService = ServiceFactory.getTaskService();

    @Test
    public void testRequestForAddTaskIsValid(){
        Assert.assertTrue(taskService.requestForAddTaskIsValid("someName","22:00:00", "someCategory"));
    }
}
