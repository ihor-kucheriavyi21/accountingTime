package model.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class UserTest {
    @Mock
    User user;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUserConstructor() {
        user.setId(4);
        user.setName("name");
        user.setPass("pass");
        user.setIdRole(2);
        Mockito.verify(user).setId(4);
        Mockito.when(user.getId()).thenReturn(4);
        Mockito.when(user.getName()).thenReturn("name");
        Mockito.when(user.getPass()).thenReturn("pass");
        Mockito.when(user.getIdRole()).thenReturn(2);
        Assert.assertEquals(4, user.getId());
        Assert.assertEquals("name", user.getName());
        Assert.assertEquals("pass", user.getPass());
        Assert.assertEquals(2, user.getIdRole());
    }

    @Test
    public void test() {
        User user = new User(1, "name", "pass", 2);
        Assert.assertEquals("User{id=1, name='name', pass='pass', idRole=2}", user.toString());
    }
}
