package com.lisa.webconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/login");
        registry.addViewController("/login");
        registry.addViewController("/customer/index.html");
        registry.addViewController("/customer/sale.html");
        registry.addViewController("/customer/result.html");
        registry.addViewController("/employee/index.html");
        registry.addViewController("/employee/sale.html");
        registry.addViewController("/employee/goodsreceipt.html");
        registry.addViewController("/employee/reports.html");

    }
}