package rabbitmq.rpcclient.client;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BackSender {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendBackMessage(String msg){
        System.out.println("rpc服务端发送消息 ");
        rabbitTemplate.convertAndSend("order-backdirect","order-back",msg);
    }
}
