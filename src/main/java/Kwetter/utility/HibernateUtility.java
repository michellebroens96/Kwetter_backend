package Kwetter.utility;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;

@RequestScoped
@Default
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

        sessionFactory.notify();
        sessionFactory.close();
    }

}
