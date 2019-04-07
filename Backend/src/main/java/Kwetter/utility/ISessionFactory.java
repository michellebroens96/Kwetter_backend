package Kwetter.utility;

import org.hibernate.Session;
import org.hibernate.StatelessSession;

public interface ISessionFactory
{
  Session getCurrentSession();

  Session openSession();

  StatelessSession openStatelessSession();
}
