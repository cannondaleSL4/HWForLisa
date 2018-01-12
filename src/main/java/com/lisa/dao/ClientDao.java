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
 * Created by  lisa on 31.12.17.
 */
@Service
public class ClientDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    final String SELECT_ALL = "SELECT id_client,name,password,user_group FROM client";
    final String GET_CLIENT_BY_ID = "SELECT * FROM client WHERE id_client =?";
    final String GET_CLIENT_BY_NAME = "SELECT * FROM client WHERE name =?";

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

    public Client getClientById(Integer id){
        return (Client) jdbcTemplate.queryForObject(GET_CLIENT_BY_ID,new Object[]{id}, new ClientMapper());
    }

    public Client getClientByName(String name){
        return (Client) jdbcTemplate.queryForObject(GET_CLIENT_BY_NAME,new Object[]{name}, new ClientMapper());
    }
}
