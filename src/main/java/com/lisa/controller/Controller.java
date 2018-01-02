package com.lisa.controller;

import com.lisa.dao.ClientDao;
import com.lisa.dao.DrugDao;
import com.lisa.dao.DrugStroteDao;
import com.lisa.dao.PharmacistDao;
import com.lisa.entity.Client;
import com.lisa.entity.Drug;
import com.lisa.entity.DrugStore;
import com.lisa.entity.Pharmacist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    PharmacistDao pharmacistDao;


    @RequestMapping("/client/all")
    List<Client>getAllClients(){
        return clientDao.getAllClients();
    }

    @RequestMapping("/pharmacist/all")
    List<Pharmacist>getAllPharmacists(){
        return pharmacistDao.getAllPharmacists();
    }

    @RequestMapping("/pharmacist/{id}")
    Pharmacist getPharmacist(@PathVariable("id")Integer id){
        return (Pharmacist) pharmacistDao.getPharmasist(id);
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
