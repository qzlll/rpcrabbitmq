package rabbitmq.nolossrabbitmq.producer;

import domain.BrokerMessageLog;
import domain.MessageStatus;
import domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rabbitmq.nolossrabbitmq.Util.ChangeType;
import rabbitmq.nolossrabbitmq.mapper.BrokerMessageLogMapper;

import java.util.Date;

@Component
public class OrderServer {

    @Autowired
    OrderSender orderSender;

    @Autowired
    BrokerMessageLogMapper brokerMessageLogMapper;

    public void service(Order order){
        String message_id = order.getMessage_id();
        //记录消息发送内容
        BrokerMessageLog brokerMessageLog = new BrokerMessageLog();
        brokerMessageLog.setMessage_id(message_id);
        brokerMessageLog.setMessage(ChangeType.ObejectToJsonString(order));
        brokerMessageLog.setStatus(MessageStatus.SEND);
        brokerMessageLog.setTry_count(0);
        Date nowdate = new Date();
        brokerMessageLog.setNext_retry(new Date(nowdate.getTime()+60*1000*3));
        brokerMessageLog.setCreate_time(nowdate);
        brokerMessageLog.setUpdate_time(nowdate);
        brokerMessageLogMapper.insert(brokerMessageLog);
        //发送消息
        orderSender.senOrder(order);
    }
}
