package Kwetter.services;

import Kwetter.DAO.kweet.IKweetDAO;

import javax.inject.Inject;

public class KweetService
{
    @Inject
    IKweetDAO kweetDAO;

    public void createKweet(int userid, String content)
    {
        kweetDAO.createKweet(userid, content);
    }
}
