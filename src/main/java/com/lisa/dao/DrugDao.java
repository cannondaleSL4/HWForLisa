package com.lisa.dao;

import com.lisa.entity.Drug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dima on 31.12.17.
 */
@Service
public class DrugDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    final String getAllDrug = "SELECT id_drug,drug_name FROM drug";

    public List<Drug> getAllDrug(){
        List<Drug> drugList = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(getAllDrug);
        rows.forEach(K ->{
            drugList.add(
                    Drug.builder()
                        .id_drug((Integer) K.get("id_drug"))
                        .name((String) K.get("drug_name"))
                        .build()
            );
        });
        return drugList;
    }
}
