package com.lisa.rowMappers;

import com.lisa.entity.Drug;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by dima on 06.01.18.
 */
public class DrugMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Drug drug = Drug.builder()
                .id_drug(resultSet.getInt("id_drug"))
                .name(resultSet.getString("drug_name"))
                .build();
        return drug;
    }
}
