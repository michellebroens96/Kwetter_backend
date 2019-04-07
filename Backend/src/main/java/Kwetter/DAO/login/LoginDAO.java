package Kwetter.DAO.login;

import Kwetter.models.User;
import Kwetter.utility.HibernateSessionFactory;
import org.hibernate.Session;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class LoginDAO implements ILoginDAO
{
    @Inject
    private HibernateSessionFactory sessionFactory;

    public User register(String username, String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        Session session = sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        session.save(user);
        session.getTransaction().commit();
        return user;
    }

    public User checkUsername(String username){
        User userEntity = null;

        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("username"), username));
        userEntity = session.createQuery(criteriaQuery).uniqueResult();

        return userEntity;
    }

    public User login(String username, String password)
    {
        User user = null;

        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("username"), username), criteriaBuilder.equal(root.get("password"), password));
        user = session.createQuery(criteriaQuery).uniqueResult();

        return user;
    }

}
