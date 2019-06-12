package Kwetter.dao.login;

import Kwetter.dto.UserDTO;
import Kwetter.model.Token;
import Kwetter.model.User;

public interface ILoginDAO {

    User register(String username, String password);

    User checkUsername(String username);

    UserDTO login(String username, String password);

    Token saveToken(int userId, String jwtToken);

    boolean logout();
}
