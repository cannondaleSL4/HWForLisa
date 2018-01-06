package com.lisa.rowMappers;

import com.lisa.entity.Client;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by dima on 02.01.18.
 */
public class ClientMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Client client = Client.builder()
                .id(resultSet.getInt("id_client"))
                .name(resultSet.getString("name"))
                .password(resultSet.getString("password"))
                .user_group(resultSet.getString("user_group"))
                .build();
        return client;
    }
}