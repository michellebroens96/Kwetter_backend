package Kwetter.endpoint;

import Kwetter.dao.user.IUserDAO;
import Kwetter.model.Kweet;
import Kwetter.model.User;
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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
@ServerEndpoint("/socket/{userId}")
public class WebSocketEndpoint {

    private final Map<Integer, Session> sessionMap = new ConcurrentHashMap<>();
    private final ObjectMapper mapper = new ObjectMapper();
    private int count = 0;

    @Inject
    private IUserDAO userDAO;

    @OnOpen
    public void onOpened(Session session, @PathParam("userId") int userId) {
        System.out.println(this.getClass().hashCode());
        System.out.println("New connection inbound, previously had " + sessionMap.size());
        System.out.println(userId + " now joined");
        sessionMap.put(userId, session);
        System.out.println("Count is now " + sessionMap.size());
        count++;
        System.out.println("Session count is now " + count);
    }

    @OnMessage
    public String handleTextMessage(String message) {
        System.out.println("New Text Message Received");
        sendToAllFollowing(message);
        return message;
    }

    @OnClose
    public void onClosed(Session session, @PathParam("userId") int userId) {
        sessionMap.remove(userId);
        System.out.println("Somebody left, count is now " + sessionMap.size());
    }

    public void sendToAllFollowing(String message) {
        try {
            Kweet t = mapper.readValue(message, Kweet.class);
            int placedBy = t.getUser().getUserId();

            User user = userDAO.getUserById(placedBy);
            List<User> followers = user.getFollowing();

            followers.forEach(k->{
                System.out.println("I GET INTO THE FOR LOOP");
                System.out.println(k.getUserId());
                System.out.println(sessionMap.size());
                if(sessionMap.containsKey(k.getUserId())) {
                    try {
                        System.out.println("I ALSO GET INTO THE IF STATEMENT BUT THE MESSAGE JUST DOESNT SEND LOL");
                        sessionMap.get(k.getUserId()).getAsyncRemote().sendText(mapper.writeValueAsString(t));
                    }
                    catch(JsonProcessingException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}
