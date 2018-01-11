package com.lisa.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by  lisa on 31.12.17.
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Drug implements Comparable<Drug> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_drug;

    private String name;

    // it's for json serialization!!!
    @Override
    public String toString() {
        return String.format(
                "Drug[id=%d, name='%s']",
                id_drug,name);
    }

    @Override
    public int compareTo(Drug o) {
        return name.compareTo(o.name);
    }
}
