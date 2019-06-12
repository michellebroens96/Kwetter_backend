package Kwetter.dao.login;

import Kwetter.dto.UserDTO;
import Kwetter.model.Token;
import Kwetter.model.User;
import Kwetter.utility.HibernateSessionFactory;
import org.hibernate.Session;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@RequestScoped
@Default
public class LoginDAO implements ILoginDAO {

    @Inject
    private HibernateSessionFactory sessionFactory;

    @Override
    public User register(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        Session session = sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        session.save(user);
        session.getTransaction().commit();
        return user;
    }

    @Override
    public User checkUsername(String username) {
        User user = null;

        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("username"), username));
        user = session.createQuery(criteriaQuery).uniqueResult();

        return user;
    }

    @Override
    public UserDTO login(String username, String password) {
        User user = null;

        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("username"), username),
                                         criteriaBuilder.equal(root.get("password"), password));
        user = session.createQuery(criteriaQuery).uniqueResult();

        return new UserDTO(user);
    }

    @Override
    public Token saveToken(int userId, String jwtToken) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, userId);
        Token token = new Token(user, jwtToken, null);
        session.getTransaction().begin();
            session.saveOrUpdate(token);
        session.getTransaction().commit();

        return token;
    }


    @Override
    public boolean logout() {
        return false;
    }
}
