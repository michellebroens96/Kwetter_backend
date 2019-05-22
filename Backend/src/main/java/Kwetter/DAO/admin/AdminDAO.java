package Kwetter.DAO.admin;

import Kwetter.models.User;

import javax.enterprise.inject.Default;
import java.util.List;

@Default
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
