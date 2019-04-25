package Kwetter.DAO.login;

import Kwetter.models.User;

public interface ILoginDAO
{
    User register(String username, String password);

    User checkUsername(String username);

    User login(String username, String password);

    boolean logout();
}
