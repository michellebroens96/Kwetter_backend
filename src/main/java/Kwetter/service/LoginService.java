package Kwetter.service;

import Kwetter.dao.login.ILoginDAO;
import Kwetter.dto.UserDTO;
import Kwetter.model.User;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named("LoginService")
@Default
public class LoginService {

    @Inject
    private ILoginDAO loginDAO;

    public User register(String username, String password) {
        return loginDAO.register(username, password);
    }

    public User checkUsername(String username) {
        return loginDAO.checkUsername(username);
    }

    public UserDTO login(String username, String password) {
        return loginDAO.login(username, password);
    }

    public boolean logout() {return loginDAO.logout();}

}
