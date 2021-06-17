package com.example.search.entity;

import java.util.List;

public class Ticket {
    private Integer id;
    private List<Object> tickets;

    public Ticket(Integer id, List<Object> tickets) {
        this.id = id;
        this.tickets = tickets;
    }

    public Ticket() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Object> getTickets() {
        return tickets;
    }

    public void setTickets(List<Object> tickets) {

        this.tickets = tickets;
    }
}
