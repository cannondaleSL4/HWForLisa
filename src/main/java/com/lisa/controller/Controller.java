package com.lisa.controller;

import com.lisa.dao.*;
import com.lisa.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

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
    DrugStroteDao drugStoreDao;

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
        return drugStoreDao.getAllDrug();
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

    @RequestMapping("/users/all")
    List<String> getCustomerName(){
        List<Users> clients = clientDao.getAllClients();
        return clients.stream()
               .map(Users::getName)
               .map(p -> StringUtils.capitalize(p))
               .collect((Collectors.toList()));
    }

    @RequestMapping(value ="/buy", method = RequestMethod.GET)
    Order buy(@RequestParam String[] drugname, @RequestParam String [] drugamont, @RequestParam String[] drugprice){
        return orderDao.makeBuy(drugname,drugamont,drugprice);
    }

    @RequestMapping(value = "/sell", method = RequestMethod.GET)
    @ResponseBody
    ModelAndView sell(@RequestParam String[] drugname, @RequestParam String [] drugamont, @RequestParam String[] drugprice,@RequestParam String customername){
        orderDao.makeSell(drugname,drugamont,drugprice,customername);
        return new ModelAndView("redirect:employee/reports.html");
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ResponseBody
    ModelAndView addDrugToStore(@RequestParam String[] drugname, @RequestParam String [] drugamont, @RequestParam String[] drugprice){
        HashSet<String> allDrugsName = drugStoreDao.getAllDrug().stream().map(DrugStore::getDrug_name).collect(Collectors.toCollection(HashSet::new));
        for(int i=0; i< drugname.length;i++){
            if(!drugamont[i].equals("0"))
                if (allDrugsName.contains(drugname[i])) drugStoreDao.update(drugname[i],drugamont[i],drugprice[i]);
                else drugStoreDao.addToStore(drugname[i],drugamont[i],drugprice[i]);
        }
        return new ModelAndView("redirect:employee/index.html");
    }

}
