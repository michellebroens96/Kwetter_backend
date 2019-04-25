package Kwetter.DAO.admin;

import Kwetter.models.User;

import java.util.List;

public class AdminDAO implements IAdminDAO {

    @Override
    public boolean deleteKweet() {
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }
}
