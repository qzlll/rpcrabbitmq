package rabbitmq.rpcclient.mapper;

import domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderMapper {

    public void insert(Order order);

    public void selectById(int id);
}
