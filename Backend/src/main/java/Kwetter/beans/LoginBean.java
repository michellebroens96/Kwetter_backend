package Kwetter.beans;

import Kwetter.dao.login.ILoginDAO;
import Kwetter.dto.UserDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class LoginBean {

    @Inject
    ILoginDAO loginDAO;

    public UserDTO login(String username, String password) {
        return loginDAO.login(username, password);
    }
}
