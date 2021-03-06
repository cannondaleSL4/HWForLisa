package com.lisa.dao;

import com.lisa.entity.Drug;
import com.lisa.entity.Order;
import javafx.util.Pair;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by lisa on 12.01.18.
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

    final String UPDATE = "UPDATE drug_store SET amount = amount - ? WHERE id_drug =?;";

    public void makeSell(String []drugname,String []drugamont,String []drugprice,String customername){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String pharmacistName = authentication.getName();
        String[] users = {customername.toLowerCase(),pharmacistName};
        sell(makeOrder(drugname,drugamont,drugprice,users));
    }

    public Order makeBuy(String[] drugname, String [] drugamont, String[] drugprice){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        String[] users = {userName,null};
        return sell(makeOrder(drugname,drugamont,drugprice,users));
    }

    private Order makeOrder(String[] drugname, String [] drugamont, String[] drugprice,String [] users){
        Map<Drug,Pair<Integer,BigDecimal>> temproryMap = new LinkedHashMap<>();
        for(int i=0; i<drugname.length;i++){
            if(!drugamont[i].equals("0")){
                Pair<Integer,BigDecimal> pair = new Pair<>(new Integer(drugamont[i]), new BigDecimal(drugprice[i]));
                Drug drug = drugDao.getByName(drugname[i]);
                temproryMap.put(drug,pair);
            }
        }

        String userName = users[0];
        String phamacistName = StringUtils.isBlank(users[1])?"default":users[1];

        Map<String,Object>params = new HashMap<>();
        params.put("id_client",clientDao.getClientByName(userName).getId_client());
        params.put("id_pharmacist",pharmacistDao.getPharmasistByName(phamacistName).getId_pharmacist());
        SimpleJdbcInsert insertOrder = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("order_t")
                .usingGeneratedKeyColumns("id_order")
                .usingColumns("id_client","id_pharmacist");
        String id = String.valueOf(insertOrder.executeAndReturnKeyHolder(params).getKeys().get("id_order"));

        Order order =Order.builder()
                .id_order(Integer.parseInt(id))
                .clientName(userName)
                .pharmasyName(phamacistName)
                .sells(temproryMap)
                .build();

        return order;
    }



    private Order sell(Order order){
        Map<Drug,Pair<Integer,BigDecimal>> localMap = new TreeMap<>(order.getSells());
        localMap.forEach((k, v) -> {
            Map<String,Object>params = new HashMap<>();
            params.put("id_order",order.getId_order());
            params.put("id_drug",k.getId_drug());
            params.put("price",v.getValue());
            params.put("amount",v.getKey());

            SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                    .withTableName("order_items")
                    .usingGeneratedKeyColumns("id_items")
                    .usingColumns("id_order","id_drug","price","amount");

            insert.execute(params);

            jdbcTemplate.update(UPDATE,v.getKey(),k.getId_drug());
        });

        return order;
    }



}
