package com.example.myBusTicketData.entitys;

import lombok.Data;

import java.io.Serializable;

@Data
public class Ticket implements Serializable {
    private String _id;
    private String title;
    private String from;
    private String to;
    private String time;
    private Integer price;
}