package Kwetter.DAO.admin;

import Kwetter.models.User;

import java.util.List;

public interface IAdminDAO {

    boolean deleteKweet();

    List<User> getAllUsers();
}
