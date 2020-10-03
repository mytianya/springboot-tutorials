package vip.codehome.springboot.tutorials.config;

import lombok.extern.slf4j.Slf4j;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author:
 * @description:
 * @creatTime: 2020/9/4--22:08
 */
@ServerEndpoint("/server")
@Slf4j
public class WebsocketConfig {
    private static final Map<String, Session> clients=new ConcurrentHashMap<>();
    @OnOpen
    public void connect(Session session){
        String userId=session.getQueryString();
        try{
            clients.remove(userId);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        clients.put(userId,session);
    }
}
