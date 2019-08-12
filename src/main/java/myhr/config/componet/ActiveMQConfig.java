package myhr.config.componet;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.activemq.command.ActiveMQQueue;
import javax.jms.Queue;

@Configuration
public class ActiveMQConfig {

    @Bean
    Queue queue(){

        return new ActiveMQQueue("amq");
    }
}
