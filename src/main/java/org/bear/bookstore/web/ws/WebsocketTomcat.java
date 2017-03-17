package org.bear.bookstore.web.ws;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/echo/{zbName}/{uname}", configurator=GetHttpSessionConfigurator.class)
public class WebsocketTomcat {
	//主播对应的用户
	private static Map<String, List<String>> zb_u = new ConcurrentHashMap<>();
	//用户对应主播
	private static Map<String, String> u_zb = new ConcurrentHashMap<>();
	//用户对应session
	private static Map<String, Session> u_session = new ConcurrentHashMap<>();
	
	@OnOpen
	public void onOpen(Session session, EndpointConfig config, 
			@PathParam("zbName") String zbName,
			@PathParam("uname") String uname) throws IOException {
		if(!zb_u.containsKey(zbName)){
			zb_u.put(zbName, new LinkedList<>());
		}
		if(!zb_u.get(zbName).contains(uname)){
			zb_u.get(zbName).add(uname);
			u_zb.put(uname, zbName);
		}
		
		u_session.put(uname, session);
		
		
		for(String name : zb_u.get(zbName)){
			u_session.get(name).getBasicRemote().sendText("cmt:" + zb_u.get(zbName).size());
			u_session.get(name).getBasicRemote().sendText(uname + " 加入直播室");
		}
	}

	@OnMessage
	public void onMessage(String message, Session session,
			@PathParam("zbName") String zbName,
			@PathParam("uname") String uname) throws IOException {
		for(String name : zb_u.get(zbName)){
			u_session.get(name).getBasicRemote().sendText(uname + "：<br/>" + message);
		}
	}

	@OnError
	public void onError(Throwable t) {
		// 以下代码省略...
	}

	@OnClose
	public void onClose(Session session, CloseReason reason,
			@PathParam("zbName") String zbName,
			@PathParam("uname") String uname) {
		u_zb.remove(uname);
		u_session.remove(uname);
		zb_u.get(zbName).remove(uname);
		
		for(String name : zb_u.get(zbName)){
			try {
				u_session.get(name).getBasicRemote().sendText("cmt:" + zb_u.get(zbName).size());
				u_session.get(name).getBasicRemote().sendText(uname + " 离开直播室");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
