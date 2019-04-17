package Kwetter.DAO.kweet;

import Kwetter.DTO.KweetDTO;

import java.util.List;

public interface IKweetDAO {

    void createKweet(int userid, String content);

    List<KweetDTO> searchKweet(String searchContent);

    List<KweetDTO> getTimeLine(int userId);

    List<KweetDTO> getLatestKweets(int userId);
}
