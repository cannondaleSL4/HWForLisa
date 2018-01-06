package com.lisa.entity;

import javafx.util.Pair;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by dima on 02.01.18.
 */
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

    private Map<Drug,Pair<Integer,BigDecimal>> sells; // first is Integer amount and second is price
}
