package com.lisa.rowMappers;

import com.lisa.entity.Pharmacist;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by dima on 02.01.18.
 */
public class PharmacyMapper  implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Pharmacist pharmacist = Pharmacist.builder()
                .id_pharmacist(resultSet.getInt("id_pharmacist"))
                .name(resultSet.getString("name"))
                .password(resultSet.getString("password"))
                .user_group(resultSet.getString("user_group")).build();
        return pharmacist;
    }
}
