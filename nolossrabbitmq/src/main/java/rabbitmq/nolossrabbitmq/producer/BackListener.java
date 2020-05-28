package rabbitmq.nolossrabbitmq.producer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class BackListener {

    @RabbitListener(queues = "order-back")
    @RabbitHandler
    public void backListen(@Payload String msg, @Headers Map<String, Object> headers, Channel channel) throws IOException {
        System.out.println("rpc客户端收到返回消息: ");
        long delivery_tag = (long)headers.get(AmqpHeaders.DELIVERY_TAG);
        boolean mutiple = false;
        channel.basicAck(delivery_tag,false);
        System.out.println(msg);
    }
}
