package rabbitmq.nolossrabbitmq.task;

import domain.BrokerMessageLog;
import domain.MessageStatus;
import domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import rabbitmq.nolossrabbitmq.Util.ChangeType;
import rabbitmq.nolossrabbitmq.mapper.BrokerMessageLogMapper;
import rabbitmq.nolossrabbitmq.producer.OrderSender;

import java.util.Date;
import java.util.List;

@Component
public class ResendTask {

    @Autowired
    BrokerMessageLogMapper brokerMessageLogMapper;
    @Autowired
    OrderSender orderSender;

    //搜索发送中且没过期的消息并重新发送
    //@Scheduled(fixedRate=3000)
    public void resendMessage(){
        System.out.println("-------------定时任务开启-----------");
        List<BrokerMessageLog> messages = brokerMessageLogMapper.findResendMessage();
        for (BrokerMessageLog message:messages) {
            if(message.getTry_count()<3){
                brokerMessageLogMapper.update4Resend(message.getMessage_id(),new Date());
                Order order = ChangeType.JsonStringToOrder(message.getMessage());
                orderSender.senOrder(order);
            }
            else{
                //设置发送失败
                message.setStatus(MessageStatus.FAIL);
                brokerMessageLogMapper.changeBrokerMessageLogStatus(message.getMessage_id(),MessageStatus.FAIL,new Date());
            }
        }

    }

}
