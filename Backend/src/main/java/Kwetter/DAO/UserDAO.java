package Kwetter.DAO;

import Kwetter.utility.HibernateSessionFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserDAO
{
    @Inject
    private HibernateSessionFactory sessionFactory;


}
