package com.lisa.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by  lisa on 31.12.17.
 */
@Entity
@Getter(AccessLevel.PROTECTED)
@NoArgsConstructor
public class Pharmacist extends Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Integer id_pharmacist;

    @Builder
    public Pharmacist(String name, String password,String user_group, Integer id){
        super(name, password, user_group);
        this.id_pharmacist = id;
    }

    @Override
    public String toString() {
        return String.format(
                "Client[id=%d, name='%s', password='%s']",
                id_pharmacist, name, password);
    }
}
