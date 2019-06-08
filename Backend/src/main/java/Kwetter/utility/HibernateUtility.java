package Kwetter.utility;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

@Singleton
public class HibernateUtility {

    private SessionFactory sessionFactory;

    @PostConstruct
    private void buildSessionFactory() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @Produces
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @PreDestroy
    public void closeSessionFactory() {
        sessionFactory.close();
    }

}
