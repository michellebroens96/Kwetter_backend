package Kwetter.dao.user;

import Kwetter.dto.UserDTO;
import Kwetter.model.Token;
import Kwetter.model.User;
import Kwetter.utility.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
@Default
public class UserDAO implements IUserDAO {

    @Inject
    private HibernateSessionFactory sessionFactory;

    @Override
    public User getUserById(int userId) {
        Session session = sessionFactory.getCurrentSession();

        User user = session.get(User.class, userId);
        return user;
    }

    @Override
    public UserDTO getUserDTOById(int userId) {
        Session session = sessionFactory.getCurrentSession();

        User user = session.get(User.class, userId);

        return new UserDTO(user);
    }

    @Override
    public boolean follow(int followingId, int followerId) {
        User user1;
        User user2;
        user1 = getUserById(followingId);
        user2 = getUserById(followerId);
        user1.AddFollower(user2);

        try {
            Session session = sessionFactory.getCurrentSession();
            session.getTransaction().begin();
            session.merge(user1);
            session.getTransaction().commit();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public UserDTO editUser(int userId, UserDTO userJson) {
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

    @Override
    public boolean checkToken(String givenToken) {
        Session session = sessionFactory.getCurrentSession();

        Token token = (Token) session.createQuery("from Token where givenToken = :givenToken ")
                                     .setParameter("token", givenToken).getSingleResult();
        if(token != null) {
            return true;
        }
        return false;
    }

    @Override
    public int getRoleId(int userId) {
        Session session = sessionFactory.getCurrentSession();

        User user = session.get(User.class, userId);

        return user.getRole().ordinal();
    }

    @Override
    public List<UserDTO> getFollowing(int userId) {
        Session session = sessionFactory.getCurrentSession();
        Query<UserDTO> query = session.createQuery("FROM User u " +
                                          "JOIN u.following AS f " +
                                          "WHERE u.userId = :userId", UserDTO.class)
                                      .setParameter("userId", userId);

        return new ArrayList<>(query.getResultList());
    }

    @Override
    public List<UserDTO> getFollowers(int userId) {
        Session session = sessionFactory.getCurrentSession();
        Query<UserDTO> query = session.createQuery("FROM User u " +
                                                "JOIN u.following AS f " +
                                                "WHERE f.userId = :userId", UserDTO.class)
                                      .setParameter("userId", userId);
        return new ArrayList<>(query.getResultList());
    }

    @Override
    public boolean unfollowUser(int userId) {
        return false;
    }
}
