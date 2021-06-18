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


    @GetMapping(value = "/{from}/{to}/{day}/{month}/{year}")
    public List<Number> getData(@PathVariable("from") final String from, @PathVariable("to") final String to, @PathVariable("day") final String day, @PathVariable("month") final String month, @PathVariable("year") final String year) {
        String Key = from + "-" + to + "-" + day + "/" + month + "/" + year;

        List<Number> price = new ArrayList();

        price = redisTemplate.opsForHash().values(Key);
        //price = redisTemplate.opsForValue().get(Key);
        if (price.isEmpty()) {
             price = restTemplate.getForObject("http://myBusTicketData-service/" + from + "/" + to + "/" + day + "/" + month + "/" + year, List.class);
        }
        return price;
    }
}
