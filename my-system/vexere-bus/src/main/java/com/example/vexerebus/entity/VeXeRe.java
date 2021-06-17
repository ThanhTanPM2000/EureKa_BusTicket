package com.example.vexerebus.entity;

import lombok.Data;


import java.util.Date;

@Data
public class VeXeRe {
    private Integer id;
    private String title;
    private String from;
    private String to;
    private String time;
    private Integer price;
}
