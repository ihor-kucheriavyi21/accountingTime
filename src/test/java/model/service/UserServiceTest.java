package model.service;

import model.entity.User;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserServiceTest {
    private UserService userService = new UserService();

    @Test
    public void testRequestIsValidByPass() {
        Assert.assertTrue(userService.requestIsValidByPass("3333", "3333"));
    }

    @Test
    public void testRequestIsValidByFillingFields() {
        Assert.assertTrue(userService.requestIsValidByFillingFields("someName", "3333"));
    }

    @Test
    public void testRequestIsValidByName() {
        Assert.assertTrue(userService.requestIsValidByName("someName"));
    }

    @Test(enabled = false)
    public void testNGAnnotations() {
        Assert.assertEquals("NullPointer", userService.getUser(new User()));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testException() {
        Assert.assertEquals("null", userService.checkIfExist("someName", "somePass"));
    }

    @Test(dependsOnMethods = {"testRequestIsValidByPass"})
    public void testDepends() {
        Assert.assertFalse(userService.requestIsValidByPass("3333", "4444"));
    }

}
