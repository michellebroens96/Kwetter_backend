package Kwetter.DAO.kweet;

import Kwetter.DAO.user.UserDAO;
import Kwetter.DTO.KweetDTO;
import Kwetter.models.Kweet;
import Kwetter.models.User;
import Kwetter.utility.HibernateSessionFactory;
import org.hibernate.Session;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
public class KweetDAO implements IKweetDAO
{
    @Inject
    private HibernateSessionFactory sessionFactory;

    @Inject
    private UserDAO userDAO;

    public void createKweet(int userid, String content)
    {
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
    public List<KweetDTO> searchKweet(String searchContent)
    {
        Session session = sessionFactory.getCurrentSession();
        List<KweetDTO> kweets = new ArrayList<>();
        Query query = session.createQuery("from Kweet k where k.content like '%'||content||'%'");

        for (Kweet kweet : (List<Kweet>) query.getResultList())
        {
            kweets.add(new KweetDTO(kweet));
        }
        return kweets;
    }
}
