package Kwetter.dao.user;

import Kwetter.dto.FollowerDTO;
import Kwetter.dto.UserDTO;
import Kwetter.model.User;

import java.util.List;

public interface IUserDAO
{
    User getUserById(int userId);

    UserDTO getUserDTOById(int userId);

    boolean follow(int followingId, int followerId);

    UserDTO editUser(int userId, UserDTO userJson);

    boolean checkToken(String token);

    int getRoleId(int userId);

    List<FollowerDTO> getFollowing(int userId);

    List<FollowerDTO> getFollowers(int userId);

    boolean unfollowUser(int userId);
}
