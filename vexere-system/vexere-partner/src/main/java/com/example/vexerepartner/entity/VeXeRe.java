package com.example.vexerepartner.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "vexere")
public class VeXeRe {
    @Id
    @JsonSerialize(using= ToStringSerializer.class)
    private ObjectId _id;
    private String Title;
    private String From;
    private String To;
    private String Time;
    private Integer Price;

    public VeXeRe(ObjectId _id, String Title, String From, String To, String Time, Integer Price) {
        this._id = _id;
        this.Title = Title;
        this.From = From;
        this.To = To;
        this.Time = Time;
        this.Price = Price;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String From) {
        this.From = From;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String To) {
        this.To = To;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer Price) {
        this.Price = Price;
    }
}
