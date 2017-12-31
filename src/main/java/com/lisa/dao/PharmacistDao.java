package com.lisa.dao;

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
public class PharmacistDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    final String getAllPharmacist = "Select id_pharmacist,name,password,user_group from pharmacist";

    public List<com.lisa.entity.Pharmacist> getAllPharmacists(){
        List<com.lisa.entity.Pharmacist> pharmacists = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(getAllPharmacist);
        for (Map row : rows){
            pharmacists.add(com.lisa.entity.Pharmacist.builder()
                    .id_pharmacist((Integer) row.get("id_pharmacist"))
                    .name((String) row.get("name"))
                    .password((String) row.get("password"))
                    .user_group((String)row.get("user_group"))
                    .build()
            );
        }
        return pharmacists;
    }

}
