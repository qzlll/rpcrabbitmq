package rabbitmq.nolossrabbitmq;

import domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import rabbitmq.nolossrabbitmq.mapper.OrderMapper;
import rabbitmq.nolossrabbitmq.producer.OrderSender;
import rabbitmq.nolossrabbitmq.producer.OrderServer;

import java.util.UUID;

@Controller
public class OrderController {

    @Autowired
    OrderServer orderServer;

    @Autowired
    OrderMapper orderMapper;

    @RequestMapping("/order/sendmessage")
    public void orderMessage(){
        Order order = new Order();
        order.setName("一个订单");
        order.setCommodity_id(1001);
        order.setBuynumber(3);
        order.setMessage_id(System.currentTimeMillis()+"$"+ UUID.randomUUID().toString());
        orderMapper.insert(order);
        orderServer.service(order);
    }
}
