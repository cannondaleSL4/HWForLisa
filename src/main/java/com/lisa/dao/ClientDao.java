package com.lisa.dao;

import com.lisa.entity.Client;
import com.lisa.entity.Users;
import com.lisa.rowMappers.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dima on 31.12.17.
 */
@Service
public class ClientDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    final String SELECT_ALL = "SELECT id_client,name,password,user_group FROM client";
    final String getClientById = "SELECT * FROM client WHERE id_client =?";

    public List<Users> getAllClients(){
        List<Users> clients = new ArrayList<>();
        jdbcTemplate.queryForList(SELECT_ALL).forEach(K ->{
            clients.add(Client.builder()
                    .id((Integer) K.get("id_client"))
                    .name((String) K.get("name"))
                    .password((String) K.get("password"))
                    .user_group((String)K.get("user_group"))
                    .build()
            );
        });
        return clients;
    }

    public Client getClient(Integer id){
        return (Client) jdbcTemplate.queryForObject(getClientById,new Object[]{id}, new ClientMapper());
    }
}
