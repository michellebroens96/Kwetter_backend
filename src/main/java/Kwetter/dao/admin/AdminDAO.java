package Kwetter.dao.admin;

import Kwetter.model.User;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import java.util.List;


@RequestScoped
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
