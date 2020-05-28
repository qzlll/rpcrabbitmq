package rabbitmq.rpcclient.client;

import com.rabbitmq.client.Channel;
import domain.Commodity;
import domain.Order;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import rabbitmq.rpcclient.mapper.CommodityMapper;

import java.io.IOException;
import java.util.Map;

@Component
public class OrderListener {

    @Autowired
    CommodityMapper commodityMapper;
    @Autowired
    BackSender backSender;

    @RabbitListener(queues = "order-test")
    @RabbitHandler
    public void consume(@Payload Order order, @Headers Map<String,Object> headers, Channel channel) throws IOException {
        //疑问：rabbitmq时如何将消息中携带的order序列化与反序列化的
        //消费消息，注意不要重复消费
        System.out.println("rpc服务端收到消息: ");
        System.out.println("消费消息");
        System.out.println("消息id："+order.getId());
        String message_id = order.getMessage_id();

        //作为rpc服务端发送响应消息
        //业务代码
        int commodityid = order.getCommodity_id();
        int buynumber = order.getBuynumber();
        commodityMapper.ChangeNumber(commodityid,buynumber);
        Commodity commodity = commodityMapper.SelectById(commodityid);
        String msg = "订单已完成，当前库存数为:"+commodityMapper.SelectById(commodityid).getNumber();
        System.out.println(msg);
        backSender.sendBackMessage(msg);

        long delivery_tag = (long)headers.get(AmqpHeaders.DELIVERY_TAG);
        //是否将比当前delivery_tag小的消息一并确认
        boolean mutiple = false;
        channel.basicAck(delivery_tag,mutiple);


    }
}
