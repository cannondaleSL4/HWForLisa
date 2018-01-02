package com.lisa.dao;

import com.lisa.entity.Client;
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
public class ClientDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    final String getAllClient = "SELECT id_client,name,password,user_group FROM client";

    public List<Client> getAllClients(){
        List<Client> clients = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(getAllClient);
        for (Map row : rows){
            clients.add(Client.builder()
                    .id_client((Integer) row.get("id_client"))
                    .name((String) row.get("name"))
                    .password((String) row.get("password"))
                    .user_group((String)row.get("user_group"))
                    .build()
            );
        }
        return clients;
    }
}
