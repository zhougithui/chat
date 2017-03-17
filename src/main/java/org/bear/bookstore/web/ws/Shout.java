package org.bear.bookstore.web.ws;

//import lombok.Data;

//@Data
public class Shout {
	private String message;
	private int onlineCount;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getOnlineCount() {
		return onlineCount;
	}

	public void setOnlineCount(int onlineCount) {
		this.onlineCount = onlineCount;
	}
	
}
