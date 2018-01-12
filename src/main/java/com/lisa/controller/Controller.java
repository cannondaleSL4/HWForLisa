package com.lisa.controller;

import com.lisa.dao.*;
import com.lisa.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by  lisa on 31.12.17.
 */
@RestController
public class Controller {
    @Autowired
    ClientDao clientDao;

    @Autowired
    DrugDao drugDao;

    @Autowired
    DrugStroteDao drugStore;

    @Autowired
    PharmacistDao pharmacistDao;

    @Autowired
    ReportDao reportDao;

    @Autowired
    OrderDao orderDao;


    @RequestMapping("/client/all")
    List<Users>getAllClients(){
        return clientDao.getAllClients();
    }

    @RequestMapping("/pharmacist/all")
    List<Users>getAllPharmacists(){
        return pharmacistDao.getAllPharmacists();
    }

    @RequestMapping("/pharmacist/{id}")
    Pharmacist getPharmacist(@PathVariable("id")Integer id){
        return pharmacistDao.getPharmacistById(id);
    }

    @RequestMapping(value ="/drug/all", method = RequestMethod.GET)
    List<Drug>getAllDrugs(){
        return drugDao.getAllDrug();
    }

    @RequestMapping("/drugstore/all")
    List<DrugStore>getAllDrugStore(){
        return drugStore.getAllDrug();
    }

    @RequestMapping("orders/all")
    List<Order> getAllOrders(){
        return reportDao.getAllorders();
    }

    @RequestMapping("/best/buyer")
    LinkedHashMap<String,BigDecimal> getBestBuyer(){
        return reportDao.getBestBuyer();
    }

    @RequestMapping("/best/seller")
    LinkedHashMap<String,BigDecimal> getBestSeller(){
        return reportDao.getBestSeller();
    }

    @RequestMapping(value ="/buy", method = RequestMethod.GET)
    Order sale(@RequestParam String[] drugname, @RequestParam String [] drugamont, @RequestParam String[] drugprice){
        String [] users = new String [2];
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        users[0] = authentication.getName();
        return orderDao.makeBuy(drugname,drugamont,drugprice);
    }

}
