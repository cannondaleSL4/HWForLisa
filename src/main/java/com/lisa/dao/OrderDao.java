package com.lisa.dao;

import com.lisa.entity.Drug;
import com.lisa.entity.Order;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by dima on 12.01.18.
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

//    final String MAKE_SELL = "INSERT into order_t (id_client,id_pharmacist) VALUES ((SELECT id_client FROM client WHERE name =?),(SELECT id_pharmacist FROM pharmacist WHERE name = ?));";
//    final String INSERT_ITEMS  = "INSERT into "+
//            "order_items (id_order,id_drug,price,amount) "+
//            "values(?,?,?)";
//
//    final String INSERT


    public Order makeBuy(String[] drugname, String [] drugamont, String[] drugprice){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        Map<String,Object>params = new HashMap<>();
        params.put("id_client",clientDao.getClientByName(userName).getId_client());
        params.put("id_pharmacist",pharmacistDao.getPharmasistByName("default").getId_pharmacist());
        SimpleJdbcInsert insertOrder = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("order_t")
                .usingGeneratedKeyColumns("id_order")
                .usingColumns("id_client","id_pharmacist");
        String id = String.valueOf(insertOrder.executeAndReturnKeyHolder(params).getKeys().get("id_order"));

        Map<Drug,Pair<Integer,BigDecimal>> temproryMap = new LinkedHashMap<>();
        for(int i=0; i<drugname.length-1;i++){
            if(!drugamont[i].equals("0")){
                Pair<Integer,BigDecimal> pair = new Pair<>(new Integer(drugamont[i]), new BigDecimal(drugamont[i]));
                Drug drug = drugDao.getByName(drugname[i]);
                temproryMap.put(drug,pair);
            }
        }

        Order order =Order.builder()
                .id_order(Integer.parseInt(id))
                .clientName(userName)
                .sells(temproryMap)
                .build();

        return sell(order);
    }

    public Order sell(Order order){
        Map<Drug,Pair<Integer,BigDecimal>> localMap = new TreeMap<>(order.getSells());
        localMap.forEach((k, v) -> {
            Map<String,Object>params = new HashMap<>();
            params.put("id_order",order.getId_order());
            params.put("id_drug",k.getId_drug());
            params.put("price",v.getKey());
            params.put("amount",v.getValue());

            SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                    .withTableName("order_items")
                    .usingGeneratedKeyColumns("id_items")
                    .usingColumns("id_order","id_drug","price","amount");
        });

        return order;
    }



}
