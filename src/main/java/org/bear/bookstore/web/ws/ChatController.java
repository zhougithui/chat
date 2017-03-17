package org.bear.bookstore.web.ws;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller  
public class ChatController {  
  
    //@MessageMapping("/zhubo1")
    public Shout handleShout(Shout incoming) {  
        return incoming;  
    }  
    
    
    //@MessageMapping("/zhubo2")
    public Shout handleShout1(Shout incoming) {  
        return incoming;  
    } 
    
    @RequestMapping("/zb/{zbName}")
    public String gotoZb(@PathVariable String zbName, Model model){
    	model.addAttribute("zbName", zbName);
    	model.addAttribute("uname", "a" + System.currentTimeMillis());
    	return "zhubo";
    }
}  