package com.lisa.dao;

import com.lisa.entity.DrugStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dima on 31.12.17.
 */
@Service
public class DrugStroteDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    final String getAllStore = "SELECT id_drug_store,drug.drug_name,price,amount  " +
            "FROM drug_store " +
            "INNER JOIN drug "+
            "ON drug_store.id_drug = drug.id_drug "+
            "WHERE amount >= 0;";

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
        return drugStoreList;
    }
}
