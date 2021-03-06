package com.example.search.controller;

import com.example.search.entitys.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private Environment env;

    @GetMapping(value = "/{from}/{to}")
    public List<Ticket> getData(@PathVariable("from") final String from, @PathVariable("to") final String to) {
        String Key = from + "-" + to;

        List<Ticket> ticketBus = new ArrayList();

        ticketBus = redisTemplate.opsForHash().values(Key);
        if(ticketBus.size() == 0){
            List<Ticket> searchData = restTemplate.getForObject("http://myBusTicketData-service/" + from +"/" + to, List.class);
            return searchData;
        }
        return ticketBus;
    }
}
