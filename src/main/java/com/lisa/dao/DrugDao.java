package com.lisa.dao;

import com.lisa.entity.Drug;
import com.lisa.rowMappers.DrugMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by  lisa on 31.12.17.
 */
@Service
public class DrugDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    final String GET_ALL_DRUG = "SELECT id_drug,drug_name FROM drug";
    final String GET_DRUG_BY_ID = "SELECT * FROM drug WHERE id_drug=?";
    final String GET_BY_NAME = "SELECT * FROM drug WHERE drug_name=?";

    public List<Drug> getAllDrug(){
        List<Drug> drugList = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(GET_ALL_DRUG);
        rows.forEach(K ->{
            drugList.add(
                    Drug.builder()
                        .id_drug((Integer) K.get("id_drug"))
                        .name((String) K.get("drug_name"))
                        .build()
            );
        });
        Collections.sort(drugList);
        return drugList;
    }

    public Drug getDrugById(Integer id){
        return (Drug) jdbcTemplate.queryForObject(GET_DRUG_BY_ID,new Object[]{id}, new DrugMapper());
    }

    public Drug getByName(String name){
        return (Drug) jdbcTemplate.queryForObject(GET_BY_NAME,new Object[]{name},new DrugMapper());
    }
}
