package rabbitmq.rpcclient.mapper;

import domain.Commodity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CommodityMapper {

    void ChangeNumber(int id, int buynumber);

    Commodity SelectById(int commodityid);
}
