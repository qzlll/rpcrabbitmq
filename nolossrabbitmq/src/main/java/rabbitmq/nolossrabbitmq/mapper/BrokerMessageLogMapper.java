package rabbitmq.nolossrabbitmq.mapper;

import domain.BrokerMessageLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface BrokerMessageLogMapper {


    void changeBrokerMessageLogStatus(String messageId, int status, Date update_time);

    void insert(BrokerMessageLog brokerMessageLog);

    List<BrokerMessageLog> findResendMessage();

    void update4Resend(String message_id, Date update_time);
}
