package rabbitmq.nolossrabbitmq.producer;

import domain.MessageStatus;
import domain.Order;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rabbitmq.nolossrabbitmq.mapper.BrokerMessageLogMapper;

import java.util.Date;

@Component
public class OrderSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;

    //confirm模式下broker接收到消息将会ask给生产者
    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {

            String messageId = correlationData.getId();
            //发送消息成功
            if(ack){
                System.out.println("发送消息成功");
                Date date = new Date();
                brokerMessageLogMapper.changeBrokerMessageLogStatus(messageId, MessageStatus.SUCCESS, new Date());
            } else{
                //进行异常处理
                System.out.println("发送消息失败，进行异常处理");
            }
        }
    };

    //发送消息
    public void senOrder(Order order){
        System.out.println("rpc客户端发送消息");
        //使用rabbitTemplate构建消息模型，设置消息回调方法
        rabbitTemplate.setConfirmCallback(confirmCallback);
        //封装消息属性，包括消息id用于broker回调时标识
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(order.getMessage_id());
        rabbitTemplate.convertAndSend("order-direct","order-test",order,correlationData);
    }

}
