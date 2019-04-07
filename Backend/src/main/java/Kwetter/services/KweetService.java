package Kwetter.services;

import Kwetter.DAO.kweet.IKweetDAO;
import Kwetter.DTO.KweetDTO;

import javax.inject.Inject;
import java.util.List;

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
}
