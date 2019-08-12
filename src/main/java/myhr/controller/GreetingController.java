package myhr.controller;

import myhr.data.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    @MessageMapping("/hello")
    public void greeting(Message message) throws Exception {

        simpMessagingTemplate.convertAndSend("/topic/greetings",message);
    }
}
