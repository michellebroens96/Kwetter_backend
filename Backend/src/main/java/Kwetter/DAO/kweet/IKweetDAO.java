package Kwetter.DAO.kweet;

import Kwetter.DTO.KweetDTO;

import java.util.List;

public interface IKweetDAO
{
    void createKweet(int userid, String content);

    public List<KweetDTO> searchKweet(String searchContent);
}
