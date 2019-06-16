package Kwetter.service;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class SessionService {
    private final Map<String, Session> sessionHashMap = new ConcurrentHashMap<>();

    public SessionService(){

    }

    public Map<String, Session> getSessionHashMap() {
        return sessionHashMap;
    }

    public void addSession(String username, Session session) {
        sessionHashMap.put(username, session);
    }
}
