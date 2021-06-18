package com.example.calendar.controllers;

import com.example.calendar.entitys.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class calendarController {
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

    @GetMapping(value = "/hi")
    public String hello() {
        return "Hello";
    }
}
