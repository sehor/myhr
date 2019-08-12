package myhr.AcitveMQ;

import myhr.data.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

@Component
public class JmsComponent {

     @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;
     @Autowired
     Queue queue;
     public void sendMessage(Message message){
         jmsMessagingTemplate.convertAndSend(this.queue,message);
     }

     @JmsListener(destination = "amq")
    public void receivMessage(Message message){
         System.out.println("received: "+message);
     }
}
