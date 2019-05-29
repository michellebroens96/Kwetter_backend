package Kwetter.DAO.kweet;

import Kwetter.DAO.user.IUserDAO;
import Kwetter.DTO.KweetDTO;
import Kwetter.models.Kweet;
import Kwetter.models.User;
import Kwetter.utility.HibernateSessionFactory;
import org.hibernate.Session;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestScoped
@Default
public class KweetDAO implements IKweetDAO {

    @Inject
    private HibernateSessionFactory sessionFactory;

    @Inject
    private IUserDAO userDAO;

    public void createKweet(int userid, String content) {
        Kweet kweet = new Kweet();
        User user = new User();
        user.setUserId(userid);
        kweet.setContent(content);
        java.util.Date utilDate = new java.util.Date();
        Date date = new Date(utilDate.getTime());
        kweet.setDate(date);
        kweet.setUser(user);
        Session session = sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        session.save(kweet);
        session.getTransaction().commit();
    }

    @Override
    public List<KweetDTO> searchKweet(String searchContent) {
        Session session = sessionFactory.getCurrentSession();
        List<KweetDTO> kweets = new ArrayList<>();
        Query query = session.createQuery("from Kweet k where k.content like '%'||k.content||'%'");

        for(Kweet kweet : (List<Kweet>) query.getResultList()) {
            kweets.add(new KweetDTO(kweet));
        }
        return kweets;
    }

    @Override
    public List<KweetDTO> getTimeLine(int userId) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, userId);

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Kweet> criteriaQuery = criteriaBuilder.createQuery(Kweet.class);
        Root<Kweet> root = criteriaQuery.from(Kweet.class);
        criteriaQuery.select(root).where(criteriaBuilder.or(criteriaBuilder.equal(root.get("user"), user)));
        List<Kweet> kweets = session.createQuery(criteriaQuery).getResultList();

        for(User userEntity : user.getFollowing()) {
            criteriaQuery.select(root).where(criteriaBuilder.or(criteriaBuilder.equal(root.get("user"), userEntity)));
            kweets.addAll(session.createQuery(criteriaQuery).getResultList());
        }
        List<KweetDTO> kweetsDTO = new ArrayList<>();

        for(Kweet kweet : kweets) {
            kweetsDTO.add(new KweetDTO(kweet));
        }
        return kweetsDTO;
    }

    @Override
    public List<KweetDTO> getLatestKweets(int userId) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, userId);

        List<Kweet> kweets = session.createQuery("from Kweet k where user = :user ORDER BY date ASC")
                                    .setParameter("user", userDAO.getUserById(userId)).setMaxResults(10)
                                    .getResultList();

        List<KweetDTO> kweetsDTO = new ArrayList<>();

        for(Kweet kweet : kweets) {
            kweetsDTO.add(new KweetDTO(kweet));
        }
        return kweetsDTO;
    }
}
