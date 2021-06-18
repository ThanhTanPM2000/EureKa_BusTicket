package com.example.myBusTicketData.controller;

import com.example.myBusTicketData.entitys.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class homeController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/{from}/{to}", method = RequestMethod.GET)
    public ResponseEntity<List<Ticket>> getTicket(@PathVariable("from") final String from, @PathVariable("to") final String to) {

        String Key = from + "-" + to;

        List<Ticket> ticketBus = new ArrayList();

        ticketBus = redisTemplate.opsForHash().values(Key);
        if (ticketBus.isEmpty()) {
            CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");

            System.out.println(restTemplate.getForObject("http://vexere-service/" + from + "/" + to, List.class));
            List<Ticket> VeXeRe = circuitBreaker.run(() -> restTemplate.getForObject("http://vexere-service/" + from + "/" + to, List.class),
                    throwable -> getDefaultAlbumList());

            List<Ticket> Futa = circuitBreaker.run(() -> restTemplate.getForObject("http://futa-service/" + from + "/" + to, List.class),
                   throwable -> getDefaultAlbumList());

            System.out.println(VeXeRe);
            if (VeXeRe.size() != 0 || !VeXeRe.isEmpty() || VeXeRe != null)
                ticketBus.addAll(VeXeRe);
            if (Futa.size() != 0 || !Futa.isEmpty() || Futa != null)
                ticketBus.addAll(Futa);

            System.out.println(("Day la: " + ticketBus));
            try {
                redisTemplate.opsForHash().put(Key, Key, ticketBus);
            } catch (Exception e) {
                e.printStackTrace();
                ResponseEntity.ok(new ArrayList());
            }
        }


        return ResponseEntity.ok(ticketBus);
    }

    @RequestMapping("/hi")
    public String home() {
        return "Welcome to My BusTicket Data Service, this service merged data from futa and vexere service, to use pls add endpoint /search/from/to";
    }

    public List<Ticket> getDefaultAlbumList() {

        return new ArrayList<>();
    }
}
