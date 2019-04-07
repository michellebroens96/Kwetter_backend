package Kwetter.utility;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.cfg.Configuration;

import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class HibernateSessionFactory implements ISessionFactory
{

  @Inject
  private SessionFactory sessionFactory;

  private Session currentSession;

  public Session openSession(){
    return sessionFactory.openSession();
  }

  public Session getCurrentSession(){
    if(currentSession == null){
      currentSession = sessionFactory.openSession();
    }
    return currentSession;
  }

  public StatelessSession openStatelessSession() {
    return sessionFactory.openStatelessSession();
  }

  @PreDestroy
  private void closeSession(){
    if(currentSession!=null && currentSession.isOpen()) {
      currentSession.close();
    }
  }
}
