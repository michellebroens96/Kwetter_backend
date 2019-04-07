package Kwetter.DAO.user;

import Kwetter.DTO.UserDTO;
import Kwetter.models.User;

public interface IUserDAO
{
    User getUserById(int userId);

    UserDTO getUserDTOById(int userId);

    void follow(int followingId, int followerId);

    UserDTO editUser(int userId, UserDTO userJson);
}
