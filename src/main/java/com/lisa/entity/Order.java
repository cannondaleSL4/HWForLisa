package com.lisa.entity;

import javafx.util.Pair;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by dima on 02.01.18.
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_t")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_order;

    private String clientName;
    private String pharmasyName;

    @ElementCollection
    @Setter
    private Map<Drug,Pair<Integer,BigDecimal>> sells; // first is Integer amount and second is price
}
