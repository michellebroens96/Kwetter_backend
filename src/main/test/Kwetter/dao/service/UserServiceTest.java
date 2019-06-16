package Kwetter.dao.service;

import Kwetter.dao.user.IUserDAO;
import Kwetter.dao.user.UserDAO;
import Kwetter.dto.UserDTO;
import Kwetter.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Spy
    @InjectMocks
    private IUserDAO userDAO = new UserDAO();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void editUser() {
        User user = new User();
        user.setUserId(40);
        user.setUsername("Hewwo");
        user.setPassword("Test1");
        UserDTO userDTO = new UserDTO(user);
        when(userDAO.editUser(user.getUserId(), userDTO)).thenReturn(userDTO);

        Assert.assertEquals("mocked", user.getName());
    }
}
