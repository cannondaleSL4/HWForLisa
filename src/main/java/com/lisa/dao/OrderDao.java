package com.lisa.dao;

import com.lisa.entity.Client;
import com.lisa.entity.Drug;
import com.lisa.entity.Order;
import com.lisa.entity.Pharmacist;
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
    ClientDao clientDao;

    @Autowired
    DrugDao drugDao;

    final String GET_ALL_ORDER = "SELECT  order_t.id_order,client.id_client,pharmacist.id_pharmacist,order_items.id_drug,order_items.price,order_items.amount " +
            "FROM order_t " +
            "INNER JOIN client ON " +
            "order_t.id_client = client.id_client " +
            "INNER JOIN pharmacist ON " +
            "order_t.id_pharmacist = pharmacist.id_pharmacist " +
            "INNER JOIN order_items ON " +
            "order_t.id_order = order_items.id_order ";

    public List<Order> getAllorders(){
        List<Order> ordersList = new ArrayList<>();
        HashMap<Drug,Pair<Integer, BigDecimal>> mapForAdd = new HashMap<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(GET_ALL_ORDER);
        rows.forEach(K ->{
            System.out.println(K);
            Pair<Integer,BigDecimal> entryForAdd = new Pair<Integer,BigDecimal>((Integer)K.get("amount"),(BigDecimal) K.get("price"));
            Pharmacist pharmacist = (Pharmacist)pharmacistDao.getPharmasist((Integer)K.get("id_pharmacist"));
            Client client = (Client)clientDao.getClient((Integer)K.get("id_client"));
            mapForAdd.put(drugDao.getDrugById((Integer) K.get("id_drug")),entryForAdd);
            ordersList.add(
                    Order.builder()
                            .id_order((Integer) K.get("id_order"))
                            .clientName(client.getName())
                            .pharmasyName(pharmacist.getName())
                            .sells(mapForAdd)
                            .build()
            );
        });
        return ordersList;
    }
}
