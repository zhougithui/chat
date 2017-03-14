package org.bear.bookstore.web.ws;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;


@Controller  
public class ChatController {  
  
    @MessageMapping("/zhubo1")
    public Shout handleShout(Shout incoming) {  
        return incoming;  
    }  
    
    
    @MessageMapping("/zhubo2")
    public Shout handleShout1(Shout incoming) {  
        return incoming;  
    } 
    
}  