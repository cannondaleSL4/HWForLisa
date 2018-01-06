package com.lisa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by dima on 06.01.18.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public abstract class Users {
    public String name;
    public String password;
    public String user_group;
}
