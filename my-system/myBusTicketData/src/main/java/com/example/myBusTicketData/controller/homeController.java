package com.example.myBusTicketData.controller;

import com.example.myBusTicketData.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @Autowired
    private Environment env;

    @RequestMapping("/")
    public String home() {
        return "Welcome to My BusTicket Data Service, this service merged data from futa and vexere service, to use pls add endpoint /search/from/to";
    }

    @RequestMapping("/{from}/{to}}")
    public ResponseEntity<List<Ticket>> getTicket(@PathVariable final String from, @PathVariable final String to) {

        String Key = from + "-" + to;
        Ticket ticket = new Ticket();
        ticket.setKey(from + "-" + to);

        List<Object> ticketBus = new ArrayList();

        ticketBus = redisTemplate.opsForHash().values(Key);
        if(ticketBus.isEmpty()){
            CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");


            List<Object> VeXeRe = circuitBreaker.run(() -> restTemplate.getForObject("http://vexere-service/"+ from + "/" + to, List.class),
                    throwable -> getDefaultAlbumList());

            List<Object> Futa = circuitBreaker.run(() -> restTemplate.getForObject("http://futa-service/" + from + "/" + to, List.class),
                    throwable -> getDefaultAlbumList());

            if (VeXeRe != null)
                ticketBus.addAll(VeXeRe);
            if (Futa != null)
                ticketBus.addAll(Futa);

            try {
                redisTemplate.opsForHash().put(Key, ticket.getKey(), ticketBus);
                return ResponseEntity.ok(ticketBus);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }


        ticket.setTickets(ticketBus);
        return ticket;
    }

    public List<Object> getDefaultAlbumList() {
        return new ArrayList<>();
    }
}
