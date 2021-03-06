package com.lisa.webconfig;

import com.lisa.dao.ClientDao;
import com.lisa.dao.PharmacistDao;
import com.lisa.entity.Users;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.List;

/**
 * Created by  lisa on 29.12.17.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    ClientDao clientDao;

    @Autowired
    PharmacistDao pharmacistDao;

    @Autowired
    private CustomSuccessHandler customSuccessHandler;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        List<Users> userList = pharmacistDao.getAllPharmacists();
        userList.addAll(clientDao.getAllClients());

        userList.forEach(K ->{
            try {
                auth.inMemoryAuthentication()
                        .withUser(K.getName()).password(K.getPassword()).roles(K.getUser_group().toUpperCase());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/employee/**").access("hasRole('EMPLOYEE')")
                .antMatchers("/customer/**").access("hasRole('USER')")
                .and().formLogin().loginPage("/login").successHandler(customSuccessHandler)
                .usernameParameter("username").passwordParameter("password")
                .and().csrf()
                .and().exceptionHandling().accessDeniedPage("/Access_Denied");
    }

}
