package org.bear.bookstore.web.ws;

import java.io.IOException;
import java.util.Date;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/echo")
public class WebsocketTomcat {
	
	@OnOpen
	public void onOpen(Session session) throws IOException {
		// 以下代码省略...
	}

	@OnMessage
	public void onMessage(String message, Session session) throws IOException {
		// 以下代码省略...
		System.out.println(message);
		session.getBasicRemote().sendText("服务器信息：" + new Date().toLocaleString());
	}

	@OnError
	public void onError(Throwable t) {
		// 以下代码省略...
	}

	@OnClose
	public void onClose(Session session, CloseReason reason) {
		// 以下代码省略...
	}

}
