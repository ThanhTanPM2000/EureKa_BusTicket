package com.example.myBusTicketData.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Ticket implements Serializable {
    private Integer id;
    private String title;
    private String from;
    private String to;
    private String time;
    private Integer price;
}