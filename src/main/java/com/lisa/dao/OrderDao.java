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
import java.util.*;

/**
 * Created by  lisa on 02.01.18.
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

    final String GET_BEST_BUYER = "SELECT id_client, sum(cost) as totalsales " +
            "FROM " +
            "( " +
            "SELECT client.id_client, (order_items.price*order_items.amount)AS cost " +
            "FROM order_t  " +
            "INNER JOIN client ON order_t.id_client = client.id_client " +
            "INNER JOIN order_items ON order_t.id_order = order_items.id_order " +
            ") sales " +
            "GROUP BY id_client " +
            "ORDER BY totalsales DESC NULLS LAST;";
    
    final String GET_BEST_SELLER = "SELECT id_pharmacist, sum(cost) as totalsales " +
            "FROM " +
            "( " +
            "SELECT pharmacist.id_pharmacist, (order_items.price*order_items.amount)AS cost " +
            "FROM order_t  " +
            "INNER JOIN pharmacist ON order_t.id_pharmacist = pharmacist.id_pharmacist " +
            "INNER JOIN order_items ON order_t.id_order = order_items.id_order " +
            "where pharmacist.id_pharmacist != 1 " +
            ") sales " +
            "GROUP BY id_pharmacist " +
            "ORDER BY totalsales DESC NULLS LAST;";

    public List<Order> getAllorders(){
        List<Order> ordersList = new ArrayList<>();
        Map<Drug,Pair<Integer, BigDecimal>> mapForAdd = new LinkedHashMap<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(GET_ALL_ORDER);
        Integer count = 0;
        Integer num = 100;

        for(Map<String,Object> localmap : rows){
            Pharmacist pharmacist;
            Client client;
            Pair<Integer,BigDecimal> entryForAdd;
            num = (Integer) localmap.get("id_order");

            pharmacist = (Pharmacist)pharmacistDao.getPharmasist((Integer)localmap.get("id_pharmacist"));
            client = (Client)clientDao.getClient((Integer)localmap.get("id_client"));
            entryForAdd = new Pair<Integer,BigDecimal>((Integer)localmap.get("amount"),(BigDecimal) localmap.get("price"));

            if(!count.equals(num)){
                mapForAdd.clear();
                mapForAdd.put(drugDao.getDrugById((Integer) localmap.get("id_drug")),entryForAdd);
                Order order = Order.builder()
                        .id_order(num)
                        .clientName(client.getName())
                        .pharmasyName(pharmacist.getName())
                        .sells(new LinkedHashMap<>(mapForAdd))
                        .build();
                ordersList.add(order);
            }else{
                mapForAdd.put(drugDao.getDrugById((Integer) localmap.get("id_drug")),entryForAdd);
                ordersList.get(ordersList.size()-1).setSells(new LinkedHashMap<>(mapForAdd));
            }
            count = num;
        }
        return ordersList;
    }

    public LinkedHashMap<String,BigDecimal> getBestBuyer(){
        LinkedHashMap<String,BigDecimal> response = new LinkedHashMap<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(GET_BEST_BUYER);
        for(Map<String,Object> localmap : rows) {
            Client client = (Client)clientDao.getClient((Integer)localmap.get("id_client"));
            BigDecimal sum = ((BigDecimal) localmap.get("totalsales"));
            response.put(client.getName(),sum);
        }
        return response;
    }

    public LinkedHashMap<String,BigDecimal> getBestSeller(){
        LinkedHashMap<String,BigDecimal> response = new LinkedHashMap<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(GET_BEST_SELLER);
        for(Map<String,Object> localmap : rows) {
            Pharmacist pharmacist = (Pharmacist) pharmacistDao.getPharmasist((Integer)localmap.get("id_pharmacist"));
            BigDecimal sum = ((BigDecimal) localmap.get("totalsales"));
            response.put(pharmacist.getName(),sum);
        }
        return response;
    }

    public Order getCurrentOrder(String [] drugname,String[]dragamount,String []dragprice,String[] users){
        Map<Drug,Pair<Integer,BigDecimal>>temproryMap = new LinkedHashMap<>();
        for(int i=0; i<drugname.length-1;i++){
            if(!dragamount[i].equals("0")){
                Pair<Integer,BigDecimal> pair = new Pair<>(new Integer(dragamount[i]), new BigDecimal(dragprice[i]));
                Drug drug = drugDao.getByName(drugname[i]);
                temproryMap.put(drug,pair);
            }
        }
        Order order =Order.builder()
                .clientName(users[0])
                .pharmasyName(users[1])
                .sells(temproryMap)
                .build();
        return order;
    }
}
