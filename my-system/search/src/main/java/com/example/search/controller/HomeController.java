package com.example.search.controller;

import com.example.search.entitys.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment env;

    @RequestMapping("/")
    public List<Ticket> getData() {
        List<Ticket> searchData = restTemplate.getForObject("http://myBusTicketData-service/", List.class);
        return searchData;
    }


}
