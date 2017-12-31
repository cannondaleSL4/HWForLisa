package com.lisa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by dima on 31.12.17.
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pharmacist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_pharmacist;

    private String name;
    private String password;
    private String user_group;

    @Override
    public String toString() {
        return String.format(
                "Client[id=%d, name='%s', password='%s']",
                id_pharmacist, name, password);
    }
}
