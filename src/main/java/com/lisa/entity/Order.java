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
import java.util.Map;

/**
 * Created by dima on 02.01.18.
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_items;

    private String clientName;
    private String pharmasyName;

    private Map<Drug,Map.Entry<BigDecimal,BigDecimal>> sells; // first bigdemical is amount and second is price
}
