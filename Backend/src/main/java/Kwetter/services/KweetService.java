package Kwetter.services;

import Kwetter.DAO.kweet.IKweetDAO;
import Kwetter.DTO.KweetDTO;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@RequestScoped
@Named("KweetService")
@Default
public class KweetService
{
    @Inject
    IKweetDAO kweetDAO;

    public void createKweet(int userid, String content)
    {
        kweetDAO.createKweet(userid, content);
    }

    public List<KweetDTO> searchKweet(String searchContent)
    {
        return kweetDAO.searchKweet(searchContent);
    }

    public List<KweetDTO> getTimeLine(int userId)
    {
        return kweetDAO.getTimeLine(userId);
    }

    public List<KweetDTO> getLatestKweets(int userId)
    {
        return kweetDAO.getLatestKweets(userId);
    }
}
