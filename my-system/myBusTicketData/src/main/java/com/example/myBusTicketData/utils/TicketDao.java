package com.example.myBusTicketData.utils;

import com.example.myBusTicketData.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

public class TicketDao {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String KEY = "TICKET";

    public boolean saveTicket(Ticket ticket) {
        try {
            redisTemplate.opsForHash().put(KEY, ticket.getId().toString(), ticket);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Ticket> fetchAllTicket() {
        List<Ticket> tickets;
        tickets = redisTemplate.opsForHash().values(KEY);
        return tickets;
    }

    public List<Ticket> fetchTicket() {
        List<Ticket> tickets;
        tickets = redisTemplate.opsForHash().values(KEY);
        return tickets;
    }
}
