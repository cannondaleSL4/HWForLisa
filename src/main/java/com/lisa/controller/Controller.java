package com.lisa.controller;

import com.lisa.dao.ClientDao;
import com.lisa.dao.DrugDao;
import com.lisa.dao.DrugStroteDao;
import com.lisa.entity.Client;
import com.lisa.entity.Drug;
import com.lisa.entity.DrugStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by dima on 31.12.17.
 */
@RestController
public class Controller {
    @Autowired
    ClientDao clientDao;

    @Autowired
    DrugDao drugDao;

    @Autowired
    DrugStroteDao drugStore;


    @RequestMapping("/client/all")
    List<Client>getAllClients(){
        return clientDao.getAllClients();
    }

    @RequestMapping("/drug/all")
    List<Drug>getAllDrugs(){
        return drugDao.getAllDrug();
    }

    @RequestMapping("/drugstore/all")
    List<DrugStore>getAllDrugStore(){
        return drugStore.getAllDrug();
    }
}
