package com.lisa.dao;

import com.lisa.entity.Pharmacist;
import com.lisa.entity.Users;
import com.lisa.rowMappers.PharmacyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by  lisa on 31.12.17.
 */
@Service
public class PharmacistDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    final String SELECT_ALL = "SELECT id_pharmacist,name,password,user_group FROM pharmacist";
    final String getPharmacistByIdStr = "SELECT * FROM pharmacist WHERE id_pharmacist =?";

    public List<Users> getAllPharmacists(){
        List<Users> pharmacists = new ArrayList<>();
        jdbcTemplate.queryForList(SELECT_ALL).forEach(K ->{
            pharmacists.add(Pharmacist.builder()
                    .id((Integer) K.get("id_pharmacist"))
                    .name((String) K.get("name"))
                    .password((String) K.get("password"))
                    .user_group((String)K.get("user_group"))
                    .build()
            );
        });
        return pharmacists;
    }

    public Object getPharmasist(Integer id){
        return jdbcTemplate.queryForObject(getPharmacistByIdStr,new Object[]{id}, new PharmacyMapper());
    }

}
