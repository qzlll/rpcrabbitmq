package rabbitmq.nolossrabbitmq.Util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import domain.Order;

public class ChangeType {

    public static String ObejectToJsonString(Object object){
        String jsonString = JSON.toJSONString(object);
        return jsonString;
    }

    public static Order JsonStringToOrder(String jsonString){
        Order order = JSON.parseObject(jsonString,new TypeReference<Order>(){});
        return order;
    }
}
