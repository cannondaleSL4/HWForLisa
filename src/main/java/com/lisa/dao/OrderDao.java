package com.lisa.dao;

import com.lisa.entity.Drug;
import com.lisa.entity.Order;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dima on 02.01.18.
 */
@Service
public class OrderDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    PharmacistDao pharmacistDao;

    @Autowired
    DrugDao drugDao;

    String getAllOrdersStr = "SELECT order_t.id_order AS Number_of_Order, client.name AS Client,pharmacist.name AS Pharmacist " +
            "FROM order_t INNER JOIN client ON order_t.id_client = client.id_client " +
            "INNER JOIN pharmacist ON order_t.id_pharmacist = pharmacist.id_pharmacist";

    public List<Order> getAllorders(){
        List<Order> ordersList = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(getAllOrdersStr);
        rows.forEach(K ->{
            HashMap<Drug,Pair<Integer, BigDecimal>> mapForAdd = new HashMap<>();
            Pair<Integer,BigDecimal> entryForAdd = new Pair<Integer,BigDecimal>((Integer)K.get("amount"),(BigDecimal) K.get("price"));
            mapForAdd.put(drugDao.getDrugById((Integer) K.get("id_drug")),entryForAdd);
            ordersList.add(
                    Order.builder()
                            .id_items((Integer) K.get("id_items"))
                            .pharmasyName((String)K.get("id_pharmacist"))
                            .sells(mapForAdd)
                            .build()
            );
        });
        return ordersList;
    }
}
