package com.lisa.controller;

import com.lisa.dao.ClientDao;
import com.lisa.entity.Client;
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


    @RequestMapping("/client/all")
    List<Client>getAllClients(){
        return clientDao.getAllClients();
    }
}
