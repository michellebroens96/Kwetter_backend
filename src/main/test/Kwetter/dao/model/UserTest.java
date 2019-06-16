package Kwetter.dao.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Kwetter.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserTest {

    List<User> users = new ArrayList<>();

    @Before
    public void setUp() {
        for(int i = 0; i < 10; i++) {
            User user = new User();
            user.setUserId(i);
            user.setName("Test Persoon " + i);
            user.setUsername("Test");
            user.setPassword("Wachtwoord");

            users.add(user);
        }
    }

    @Test
    public void edit() {
        User user2 = users.get(1);
        user2.setName("Echt persoon");
        user2.setImage("Foto van een slak");
        user2.setLocation("Eindhoven");

        Assert.assertEquals("Echt persoon", user2.getName());
        Assert.assertEquals("Foto van een slak", user2.getImage());
        Assert.assertEquals("Eindhoven", user2.getLocation());
        Assert.assertEquals("Wachtwoord", user2.getPassword());
    }

    @Test
    public void follow() {
        User user1 = users.get(0);
        User user2 = users.get(1);
        user2.setName("Echt persoon");

        user1.addFollower(user2);
        Assert.assertEquals(user1.getFollowers().size(), 1);
    }

}
