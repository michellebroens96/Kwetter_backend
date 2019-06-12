package Kwetter.dao.admin;

import Kwetter.model.User;

import java.util.List;

public interface IAdminDAO {

    boolean deleteKweet();

    List<User> getAllUsers();
}
