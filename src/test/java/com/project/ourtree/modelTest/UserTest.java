package com.project.ourtree.modelTest;

import com.project.ourtree.model.User;
import org.junit.Assert;
import org.junit.Test;

public class UserTest {

    User userExample = new User("test@example.com", "password", false);

    @Test
    public void getId() {
        Assert.assertEquals(32, userExample.getId());
    }

    @Test
    public void setId() {
        userExample.setId(31);
        Assert.assertEquals(31, userExample.getId());
    }

    @Test
    public void getEmail() {
        Assert.assertEquals("test@example.com", userExample.getEmail());
    }

    @Test
    public void setEmail() {
        userExample.setEmail("toto@example.com");
        Assert.assertEquals("toto@example.com", userExample.getEmail());
    }

    @Test
    public void getPassword() {
        Assert.assertEquals("password", userExample.getPassword());
    }

    @Test
    public void setPassword() {
        userExample.setPassword("new_password");
        Assert.assertEquals("new_password", userExample.getPassword());
    }

    @Test
    public void isAdmin() {
        Assert.assertEquals(false, userExample.isAdmin());
    }
}
