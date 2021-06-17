package com.example.futabus.entity;

import lombok.Data;

@Data
public class Futa {
    private Integer id;
    private String title;
    private String from;
    private String to;
    private String time;
    private Integer price;
}
