package com.lisa.dao;

import com.lisa.entity.DrugStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by  lisa on 31.12.17.
 */
@Service
public class DrugStroteDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DrugDao drugDao;

    final String getAllStore = "SELECT id_drug_store,drug.drug_name,price,amount  " +
            "FROM drug_store " +
            "INNER JOIN drug "+
            "ON drug_store.id_drug = drug.id_drug "+
            "WHERE amount >= 0;";

    final String UPDATE = "UPDATE drug_store SET amount = amount + %s WHERE id_drug =%s;";

    public List<DrugStore> getAllDrug(){
        List<DrugStore> drugStoreList = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(getAllStore);
        rows.forEach(K ->{
            drugStoreList.add(
                    DrugStore.builder()
                            .id_drug_store((Integer) K.get("id_drug_store"))
                            .drug_name((String) K.get("drug_name"))
                            .amount((Integer) K.get("amount"))
                            .price((BigDecimal) K.get("price"))
                            .build()
            );
        });
        Collections.sort(drugStoreList);
        return drugStoreList;
    }

    public void update(String drugname, String drugamont, String drugprice){
        jdbcTemplate.update(String.format(UPDATE,drugamont,drugDao.getByName(drugname).getId_drug()));
    }

    public void addToStore(String drugname, String drugamont, String drugprice){
        Map<String,Object>params = new HashMap<>();
        params.put("drug_name",drugname);
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("drug")
                .usingGeneratedKeyColumns("id_drug")
                .usingColumns("drug_name");
        String id = String.valueOf(insert.executeAndReturnKeyHolder(params).getKeys().get("id_drug"));

        params.clear();
        params.put("drug_name",drugname);
        params.put("id_drug",id);
        params.put("price",drugprice);
        params.put("amount",drugamont);

        insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("drug_store")
                .usingGeneratedKeyColumns("id_drug_store")
                .usingColumns("drug_name","id_drug","price","amount");

        insert.execute(params);
    }
}
