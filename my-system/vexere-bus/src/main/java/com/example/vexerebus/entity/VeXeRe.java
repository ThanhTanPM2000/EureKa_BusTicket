package com.example.vexerebus.entity;

import java.util.Date;

public class VeXeRe {
    private Integer id;
    private String title;
    private String from;
    private String to;
    private String time;
    private Integer price;

    public VeXeRe(Integer id, String title, String from, String to, String time, Integer price) {
        this.id = id;
        this.title = title;
        this.from = from;
        this.to = to;
        this.time = time;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
