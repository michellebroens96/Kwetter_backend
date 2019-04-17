package Kwetter.beans;

import Kwetter.DAO.login.ILoginDAO;
import Kwetter.models.User;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class LoginBean {

    @Inject
    ILoginDAO loginDAO;

    public User login(String username, String password) {
        return loginDAO.login(username, password);
    }
}
