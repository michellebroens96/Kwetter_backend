package Kwetter.endpoint;

import Kwetter.dao.user.IUserDAO;
import Kwetter.dto.UserDTO;
import Kwetter.model.Kweet;
import Kwetter.service.SessionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
@ServerEndpoint("/socket/{userId}")
public class WebSocketEndpoint {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Inject
    private SessionService sService;

    @Inject
    private IUserDAO userDAO;

    @OnOpen
    public void onOpened(Session session, @PathParam("username") String username) {
        sService.addSession(username, session);
    }

    @OnClose
    public void onClosed(Session session, @PathParam("username") String username) {
        sService.getSessionHashMap().remove(username);
    }

    @OnMessage
    public String handleTextMessage(String message) {
        sendToAllFollowing(message);
        return message;
    }

    public void sendToAllFollowing(String message) {
        try {
            Kweet kweet = mapper.readValue(message, Kweet.class);
            Integer placedBy = kweet.getUser().getUserId();

            List<UserDTO> followers = userDAO.getFollowers(placedBy);

            followers.forEach( user -> {
                if(sService.getSessionHashMap().containsKey(user.getUserId())){
                    try {
                        sService.getSessionHashMap().get(user.getUserId())
                                .getAsyncRemote()
                                .sendText(mapper.writeValueAsString(kweet));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (IOException e) {
            Logger.getLogger(WebSocketEndpoint.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
}
