package Kwetter.dao.user;

import Kwetter.dto.UserDTO;
import Kwetter.model.User;

public interface IUserDAO
{
    User getUserById(int userId);

    UserDTO getUserDTOById(int userId);

    void follow(int followingId, int followerId);

    UserDTO editUser(int userId, UserDTO userJson);
}
