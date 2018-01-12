package com.lisa.entity;

import javafx.util.Pair;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by  lisa on 02.01.18.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_t")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_order;

    private String clientName;

    @Builder.Default
    private String pharmasyName = "default";

    @ElementCollection
    @Setter
    private Map<Drug,Pair<Integer,BigDecimal>> sells; // first is Integer amount and second is price
}
