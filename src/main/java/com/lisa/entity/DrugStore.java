package com.lisa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by dima on 31.12.17.
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DrugStore {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_drug_store;

    private String drug_name;
    private BigDecimal price;
    private BigDecimal amount;

    @Override
    public String toString() {
        return String.format(
                "drug_store[id=%d, drug_name='%s', price='%s', amount='%s']",
                id_drug_store,drug_name, price, amount);
    }
}
