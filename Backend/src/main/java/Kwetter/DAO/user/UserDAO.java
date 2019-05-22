package Kwetter.DAO.user;

import Kwetter.DTO.UserDTO;
import Kwetter.models.User;
import Kwetter.utility.HibernateSessionFactory;
import org.hibernate.Session;

import javax.enterprise.inject.Default;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

@SessionScoped
@Default
public class UserDAO implements IUserDAO
{
    @Inject
    private HibernateSessionFactory sessionFactory;

    @Override
    public User getUserById(int userId)
    {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, userId);
        return user;
    }

    @Override
    public UserDTO getUserDTOById(int userId)
    {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, userId);

        return new UserDTO(user);
    }

    @Override
    public void follow(int followingId, int followerId)
    {
        User user1;
        User user2;
        user1 = getUserById(followingId);
        user2 = getUserById(followerId);
        user1.AddFollower(user2);

        Session session = sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        session.merge(user1);
        session.getTransaction().commit();
    }

    @Override
    public UserDTO editUser(int userId, UserDTO userJson)
    {
        User user = getUserById(userId);
        user.setName(userJson.getName());
        user.setImage(userJson.getImage());
        user.setUsername(userJson.getUsername());
        user.setBio(userJson.getBio());
        user.setLocation(userJson.getLocation());
        user.setWeb(userJson.getWeb());

        Session session = sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        session.merge(user);
        session.getTransaction().commit();
        return new UserDTO(user);
    }
}
