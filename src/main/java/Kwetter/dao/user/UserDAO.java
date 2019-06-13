package Kwetter.dao.user;

import Kwetter.dto.UserDTO;
import Kwetter.model.Token;
import Kwetter.model.User;
import Kwetter.utility.HibernateSessionFactory;
import org.hibernate.Session;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
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
        User user = getUserById(followingId);
        User followThis =  getUserById(followerId);
        user.setFollowing(getFollowing(user.getUserId()));
        user.addFollower(followThis);

        UserDTO userDTO = new UserDTO(user);

        editUser(user.getUserId(), userDTO);
        return true;
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
    public List<User> getFollowing(int userId) {
        Session session = sessionFactory.getCurrentSession();
        List<User> followingList = session.createQuery("from User u " +
                                          "join u.following as f " +
                                          "where u.userId = :userId", User.class)
                                      .setParameter("userId", userId).getResultList();

        return followingList;
    }

    @Override
    public List<User> getFollowers(int userId) {
        Session session = sessionFactory.getCurrentSession();
        List<User> followerList = session.createQuery("from User u " +
                                                      "join u.following as f " +
                                                      "where f.userId = :userId", User.class)
                                         .setParameter("userId", userId).getResultList();

        return followerList;
    }

    @Override
    public boolean unfollowUser(int userId) {
        return false;
    }
}
