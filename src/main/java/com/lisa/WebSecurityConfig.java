package com.lisa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by dima on 29.12.17.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");

        auth
                .inMemoryAuthentication()
                .withUser("employee").password("employee").roles("EMPLOYEE");
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http
//                .authorizeRequests()
//                .antMatchers("/employee/**").access("hasRole('EMPLOYEE')")
//                .antMatchers("/customer/**").access("hasRole('USER')")
//                .antMatchers("/login*").permitAll()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/perform_login")
//                .defaultSuccessUrl("/customer/index.html",true)
//                .failureUrl("/login.html?error=true")
//                .and()
//                .logout()
//                .logoutSuccessUrl("/login.html");
//    }

    @Autowired
    CustomSuccessHandler customSuccessHandler;

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
