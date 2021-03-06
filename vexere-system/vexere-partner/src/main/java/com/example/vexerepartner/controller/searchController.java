package com.example.vexerepartner.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.example.vexerepartner.entity.VeXeRe;

import com.example.vexerepartner.repository.VexereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/")
public class searchController {

    @Autowired
    private VexereRepository vexereRepository;

    @Autowired
    private Environment env;

    @RequestMapping(value = "/tickets", method = RequestMethod.GET)
    public List<VeXeRe> getVexereTickets() {
        return vexereRepository.findAll();
    }

    @RequestMapping(value = "/{from}/{to}", method = RequestMethod.GET)
    public List<VeXeRe> getVexereTicketsFT(@PathVariable("from") String from, @PathVariable("to") String to) {
        try {
            TimeUnit.SECONDS.sleep(5);
            var listVexere = vexereRepository.findAll();
            List<VeXeRe> reponse = new ArrayList();
            for (VeXeRe item : listVexere) {

                if (item.getFrom().equals(from) && item.getTo().equals(to)) {
                    reponse.add(item);
                }
            }
            return reponse;
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            return new ArrayList();
        }
    }

    @GetMapping(value = "/{from}/{to}/{day}/{month}/{year}")
    public List<VeXeRe> getData(@PathVariable("from") final String from, @PathVariable("to") final String to, @PathVariable("day") final String day, @PathVariable("month") final String month, @PathVariable("year") final String year) {
        var listVexere = vexereRepository.findAll();
        List<VeXeRe> reponse = new ArrayList();
        String Key = day+"/" + month + "/" + year;
        for (VeXeRe item : listVexere) {
            if (item.getFrom().equals(from) && item.getTo().equals(to) && item.getTime().equals(Key)) {
                reponse.add(item);
            }
        }
        return reponse;
    }
}
