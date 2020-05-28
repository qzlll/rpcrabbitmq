package rabbitmq.nolossrabbitmq;

import domain.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rabbitmq.nolossrabbitmq.mapper.CommodityMapper;
import rabbitmq.nolossrabbitmq.mapper.OrderMapper;
import rabbitmq.nolossrabbitmq.producer.OrderServer;
import rabbitmq.nolossrabbitmq.task.ResendTask;

import java.util.UUID;

@SpringBootTest
class NolossRabbitmqApplicationTests {

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderServer orderServer;
    @Autowired
    ResendTask resendTask;
    @Autowired
    CommodityMapper commodityMapper;

    @Test
    void contextLoads() {
    }


    @Test
    void orderTest(){
        Order order = new Order();
        order.setName("一个订单");
        order.setCommodity_id(1001);
        order.setBuynumber(3);
        order.setMessage_id(System.currentTimeMillis()+"$"+ UUID.randomUUID().toString());
        orderMapper.insert(order);
        orderServer.service(order);

    }

    @Test
    void resendTest(){
        resendTask.resendMessage();
    }

    @Test
    void commodityTest(){
        commodityMapper.ChangeNumber(1001,2);
        commodityMapper.SelectById(1001);
        System.out.println();
    }
}
