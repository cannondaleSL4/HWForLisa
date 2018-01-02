package com.lisa.dao;

import com.lisa.entity.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dima on 02.01.18.
 */
@Service
public class OrderDao {
//    @Autowired
//    JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    PharmacistDao pharmacistDao;

    String getAllOrdersStr = "SELECT order_t.id_order AS Number_of_Order,client.name AS Client,pharmacist.name AS Pharmacist, " +
            "FROM order_t INNER JOIN client ON order_t.id_client = client.id_client " +
            "INNER JOIN pharmacist ON order_t.id_pharmacist = pharmacist.id_pharmacist";

    public List<Order> getAllorders(){
        List<Order> ordersList = new ArrayList<>();
//        List<Map<String, Object>> rows = jdbcTemplate.queryForList(getAllOrdersStr);
//        rows.forEach(K ->{
//            String pharmasyName = ((Integer)K.get("id_pharmacist")).equals(0) ? "Self orderd":pharmacistDao.getPharmasist(Integer id);
//            ordersList.add(
//                    Order.builder()
//                            .id_items((Integer) K.get("id_items"))
//                            .pharmasyName()
//                            .amount((BigDecimal) K.get("amount"))
//                            .price((BigDecimal) K.get("price"))
//                            .build()
//            );
//        });
        return ordersList;
    }
}
